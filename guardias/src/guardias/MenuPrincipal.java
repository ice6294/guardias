/**
 *                ███
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███   █  █  █  █        ██
 *                ███    ▀▀ ▀▀ ▀▀█        ██
 * ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄███▄▄▄▄▄▄▄▄▄▄▄▄█▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *     ▀▀▄▄       ███            █        ██
 *        ▀▀▄▄   ███▀            █        ██
 *           ▀▀███▀              █        ██
 *                               █▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *      Copyright (c) 2016       █  ▀▀▄▄  ██
 *      All right reserved       █     ▀▀██▀
 *                               ▀
 */
package guardias;

import static guardias.Asignar.cthulhu;
import static guardias.Utils.HELP;
import static guardias.Utils.getMonth;
import static guardias.Utils.getResidentFromToString;
import static guardias.Utils.isMonth;
import static guardias.Utils.showResident;
import static guardias.Utils.showResidents;
import static guardias.Utils.sortPairList;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.text.DefaultCaret;

/**
 * @version v1.0
 * @author luis
 */
public final class MenuPrincipal extends javax.swing.JFrame {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	public Integer year = 2016;
	public Integer month = 9;
	public Integer seed = 360;

	public Calendario calendarioActual;

	public Map<Integer, Residente> residentes = new HashMap();

	public List<Pair<Integer, Residente>> ausentes = new ArrayList();
	public List<Pair<Integer, Residente>> obligatorios = new ArrayList();

	public boolean resListOpen = false;

	public String resPath = "./residentes";
	public String absPath = "./ausentes";
	public String oblPath = "./obligatorios";
	
	public static PrintStream printOut;
	public static PrintStream printErr;
	
	public void clean() {
		this.jTextArea.setText("");
	}
	// </editor-fold>
	

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public MenuPrincipal() {
		initComponents();
		loadResis();
		loadAusentes();
		loadObligatorios();
		setComboBoxResidentes();
		
		DefaultCaret caret = (DefaultCaret) this.jTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		CTextArea taos = new CTextArea();

		// <editor-fold desc="// Redirijimos las salidas">
		printOut = new PrintStream(taos, true) {
			@Override
			public void println(String s) {
				synchronized (MenuPrincipal.this) {
					if ((s != null) && (s.length() > 0)) {
						jTextArea.setText(jTextArea.getText() + "\r\n" + s);
					}
				}
			}

			@Override
			public void print(String s) {
				synchronized (MenuPrincipal.this) {
					if ((s != null) && (s.length() > 0)) {
						jTextArea.setText(jTextArea.getText() + s);
					}
				}
			}
		};

		System.setOut(printOut);

		printErr = new PrintStream(taos, true) {
			@Override
			public void println(String s) {
				synchronized (MenuPrincipal.this) {
					if ((s != null) && (s.length() > 0)) {
						jTextArea.setText(jTextArea.getText() + "\r\n" + s);
					}
				}
			}

			@Override
			public void print(String s) {
				synchronized (MenuPrincipal.this) {
					if ((s != null) && (s.length() > 0)) {
						jTextArea.setText(jTextArea.getText() + s);
					}
				}
			}
		};
		
		System.setErr(printErr);
		// </editor-fold>
	}

	private void loadResis() {
		File f = new File(resPath);
		// Si existe el fichero, cargamos los residentes del mismo
		if (f.exists()) {
			FileInputStream in;
			ObjectInputStream ois;
			try {
				in = new FileInputStream(resPath);
				ois = new ObjectInputStream(in);
				residentes = (Map<Integer, Residente>) ois.readObject();
				ois.close();
				return;
			} catch (FileNotFoundException ex) {
				System.err.println("\r\n # Error al cargar residentes (" + ex + ")");
			} catch (IOException | ClassNotFoundException ex) {
				System.err.println("\r\n # Error al cargar residentes (" + ex + ")");
			}
		}
		// Si no existe el fichero, creamos los residentes y los guardamos
		defaultResis();
		saveResis();
	}

	public void saveResis() {
		FileOutputStream out;
		ObjectOutputStream oos;
		try {
			out = new FileOutputStream(resPath);
			oos = new ObjectOutputStream(out);
			oos.writeObject(residentes);
			oos.close();
		} catch (FileNotFoundException ex) {
			System.err.println("\r\n # Error al guardar residentes (" + ex + ")");
		} catch (IOException ex) {
			System.err.println("\r\n # Error al guardar residentes (" + ex + ")");
		}
	}
	
	private void loadAusentes() {
		File f = new File(absPath);
		// Si existe el fichero, cargamos los residentes del mismo
		if (f.exists()) {
			FileInputStream in;
			ObjectInputStream ois;
			try {
				in = new FileInputStream(absPath);
				ois = new ObjectInputStream(in);
				ausentes = (List<Pair<Integer, Residente>>) ois.readObject();
				ois.close();
			} catch (FileNotFoundException ex) {
				System.err.println("\r\n # Error al cargar ausentes (" + ex + ")");
			} catch (IOException | ClassNotFoundException ex) {
				System.err.println("\r\n # Error al cargar ausentes (" + ex + ")");
			}
		}
	}

	public void saveAusentes() {
		FileOutputStream out;
		ObjectOutputStream oos;
		try {
			out = new FileOutputStream(absPath);
			oos = new ObjectOutputStream(out);
			oos.writeObject(ausentes);
			oos.close();
		} catch (FileNotFoundException ex) {
			System.err.println("\r\n # Error al salvar ausentes (" + ex + ")");
		} catch (IOException ex) {
			System.err.println("\r\n # Error al salvar ausentes (" + ex + ")");
		}
	}
	
	private void loadObligatorios() {
		File f = new File(oblPath);
		// Si existe el fichero, cargamos los residentes del mismo
		if (f.exists()) {
			FileInputStream in;
			ObjectInputStream ois;
			try {
				in = new FileInputStream(oblPath);
				ois = new ObjectInputStream(in);
				obligatorios = (List<Pair<Integer, Residente>>) ois.readObject();
				ois.close();
			} catch (FileNotFoundException ex) {
				System.err.println("\r\n # Error al cargar obligatorios (" + ex + ")");
			} catch (IOException | ClassNotFoundException ex) {
				System.err.println("\r\n # Error al cargar obligatorios (" + ex + ")");
			}
		}
	}

	public void saveObligatorios() {
		FileOutputStream out;
		ObjectOutputStream oos;
		try {
			out = new FileOutputStream(oblPath);
			oos = new ObjectOutputStream(out);
			oos.writeObject(obligatorios);
			oos.close();
		} catch (FileNotFoundException ex) {
			System.err.println("\r\n # Error al salvar obligatorios (" + ex + ")");
		} catch (IOException ex) {
			System.err.println("\r\n # Error al salvar obligatorios (" + ex + ")");
		}
	}

	private void defaultResis() {
		residentes.clear();

		// <editor-fold desc="// Inicializar residentes">
		Residente res0 = new Residente("Pablo", 1, 5); // R5
		Residente res1 = new Residente("Javi", 2, 5); // R5
		Residente res2 = new Residente("Rosaura", 3, 4); // R4
		Residente res3 = new Residente("Joaquín", 4, 4); // R4
		Residente res4 = new Residente("Pau", 5, 3); // R3
		Residente res5 = new Residente("Dani", 6, 3); // R3
		Residente res6 = new Residente("Laura", 7, 2); // R2
		Residente res7 = new Residente("Menlu", 8, 2); // R2
		Residente res8 = new Residente("Xavi", 9, 1); // R1
		Residente res9 = new Residente("Xiana", 10, 1); // R1
		// </editor-fold>

		// <editor-fold desc="// Agregamos lista de residentes">
		residentes.put(1, res0);
		residentes.put(2, res1);
		residentes.put(3, res2);
		residentes.put(4, res3);
		residentes.put(5, res4);
		residentes.put(6, res5);
		residentes.put(7, res6);
		residentes.put(8, res7);
		residentes.put(9, res8);
		residentes.put(10, res9);
		// </editor-fold>
	}

	public void setComboBoxResidentes() {
		// <editor-fold desc="// Agregamos lista de residentes">
		this.jComboBox2.addItem("");
		List<Residente> resis = Utils.sortMap(residentes);
		for (Residente res : resis) {
			this.jComboBox2.addItem(res.toString());
		}
		// </editor-fold>
	}
	// </editor-fold>

	/**
	 * This method is called from within the constructor to initialize the form.
	 * Do not modify.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelGuardias = new javax.swing.JLabel();
        jButtonCrearCalendario = new javax.swing.JButton();
        jLabelYear = new javax.swing.JLabel();
        jTextFieldYear = new javax.swing.JTextField();
        jLabelMonth = new javax.swing.JLabel();
        jTextFieldMonth = new javax.swing.JTextField();
        jLabelSeed = new javax.swing.JLabel();
        jTextFieldSeed = new javax.swing.JTextField();
        jButtonResidentes = new javax.swing.JButton();
        jLabelResidente = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabelAusencias = new javax.swing.JLabel();
        jTextFieldAusencias = new javax.swing.JTextField();
        jButtonAddAusencias = new javax.swing.JButton();
        jButtonRmvAusencias = new javax.swing.JButton();
        jLabelObligatorias = new javax.swing.JLabel();
        jTextFieldObligatorias = new javax.swing.JTextField();
        jButtonAddObligatorias = new javax.swing.JButton();
        jButtonRmvObligatorias = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButtonExit = new javax.swing.JButton();
        jButtonClean = new javax.swing.JButton();
        jButtonRestart = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jButtonHelp = new javax.swing.JButton();
        ResEditButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guardias - Residentes (CGD) - Puerta del Hierro");
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jLabelGuardias.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabelGuardias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGuardias.setText("Guardias");

        jButtonCrearCalendario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonCrearCalendario.setText("Crear Calendario");
        jButtonCrearCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearCalendarioActionPerformed(evt);
            }
        });

        jLabelYear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelYear.setText("YEAR");

        jTextFieldYear.setBackground(new java.awt.Color(250, 250, 250));
        jTextFieldYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldYear.setText("2016");
        jTextFieldYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldYearActionPerformed(evt);
            }
        });

        jLabelMonth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMonth.setText("MONTH");

        jTextFieldMonth.setBackground(new java.awt.Color(250, 250, 250));
        jTextFieldMonth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldMonth.setText("Marzo");
        jTextFieldMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMonthActionPerformed(evt);
            }
        });

        jLabelSeed.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelSeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSeed.setText("SEED");

        jTextFieldSeed.setBackground(new java.awt.Color(250, 250, 250));
        jTextFieldSeed.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSeed.setText("360");

        jButtonResidentes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonResidentes.setText("RESIDENTES");
        jButtonResidentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResidentesActionPerformed(evt);
            }
        });

        jLabelResidente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelResidente.setText("Residente:");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabelAusencias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAusencias.setText("Ausencias:");

        jTextFieldAusencias.setBackground(new java.awt.Color(250, 250, 250));
        jTextFieldAusencias.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jButtonAddAusencias.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButtonAddAusencias.setText("+");
        jButtonAddAusencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddAusenciasActionPerformed(evt);
            }
        });

        jButtonRmvAusencias.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButtonRmvAusencias.setText("-");
        jButtonRmvAusencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRmvAusenciasActionPerformed(evt);
            }
        });

        jLabelObligatorias.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelObligatorias.setText("Obligatorias:");

        jTextFieldObligatorias.setBackground(new java.awt.Color(250, 250, 250));
        jTextFieldObligatorias.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jButtonAddObligatorias.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButtonAddObligatorias.setText("+");
        jButtonAddObligatorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddObligatoriasActionPerformed(evt);
            }
        });

        jButtonRmvObligatorias.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButtonRmvObligatorias.setText("-");
        jButtonRmvObligatorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRmvObligatoriasActionPerformed(evt);
            }
        });

        jTextArea.setBackground(new java.awt.Color(250, 250, 250));
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Courier", 1, 14)); // NOI18N
        jTextArea.setRows(5);
        jTextArea.setText("\n");
        jScrollPane.setViewportView(jTextArea);

        jButtonExit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonExit.setText("SALIR");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jButtonClean.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonClean.setText("LIMPIAR");
        jButtonClean.setToolTipText("");
        jButtonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCleanActionPerformed(evt);
            }
        });

        jButtonRestart.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonRestart.setText("REINICIAR");
        jButtonRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestartActionPerformed(evt);
            }
        });

        jButtonExport.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonExport.setText("GUARDAR");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jButtonHelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonHelp.setText("AYUDA");
        jButtonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelpActionPerformed(evt);
            }
        });

        ResEditButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ResEditButton.setText("✎");
        ResEditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResEditButtonMouseClicked(evt);
            }
        });
        ResEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResEditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonRestart, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabelResidente, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                        .addGap(26, 26, 26))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelObligatorias, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                                        .addGap(24, 24, 24))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelAusencias, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox2, 0, 175, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldObligatorias, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldAusencias))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonAddAusencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonRmvAusencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonAddObligatorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonRmvObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonResidentes, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ResEditButton)
                                .addGap(3, 3, 3)))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonCrearCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelGuardias, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSeed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSeed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(63, 63, 63)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelGuardias)
                        .addComponent(jButtonResidentes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ResEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabelResidente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonCrearCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelSeed, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldSeed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldAusencias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelAusencias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jComboBox2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonAddAusencias, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addComponent(jButtonRmvAusencias, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButtonAddObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(jButtonRmvObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1010, 719));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

	// METHODS
	// <editor-fold desc="<------------------->">
    private void jButtonCrearCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearCalendarioActionPerformed
		jTextArea.setText("");
		int _year = 2016;
		boolean ok = true;
		try {
			_year = Integer.parseInt(this.jTextFieldYear.getText());
			if (_year < 0 || _year > 5000) {
				throw new Exception();
			}
		} catch (Exception exc) {
			System.err.println("\r\n # Campo YEAR erróneo: " + _year);
			ok = false;
		}
		String _month = "Marzo";
		try {
			_month = this.jTextFieldMonth.getText();
			if (!isMonth(_month)) {
				throw new Exception();
			}
		} catch (Exception exc) {
			System.err.println("\r\n # Campo MONTH erróneo: " + _month);
			ok = false;
		}
		int _seed = 360;
		try {
			_seed = Integer.parseInt(this.jTextFieldSeed.getText());
		} catch (Exception exc) {
			System.err.println("\r\n # Campo SEED erróneo: " + _seed);
			ok = false;
		}
		year = _year;
		month = getMonth(_month);
		seed = _seed;
		if (ok) {
			final Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					jButtonCrearCalendario.setEnabled(false);
					calendarioActual = cthulhu(year, month, seed, residentes, ausentes, obligatorios);
					jButtonCrearCalendario.setEnabled(true);
				}
			}, "Chtulhu");
			final Thread wait = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						if (th.isAlive()) {
							System.err.println(" # Error: Imposible esta combinación. Pruebe con otras condiciones u otra seed.");
							Asignar.stop = true;
							System.gc();
							jButtonCrearCalendario.setEnabled(true);
						}
					} catch (InterruptedException ex) {
						Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}, "Waiter");
			th.start();
			wait.start();
		} else {
			System.err.println(" # Error: campo erróneo");
		}
    }//GEN-LAST:event_jButtonCrearCalendarioActionPerformed

    private void jButtonAddAusenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddAusenciasActionPerformed
		// Get Text
		String _ausencias = this.jTextFieldAusencias.getText();
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		// Get Res
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		Residente res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
		if (!addResTo(ausentes, _ausencias, res)) {
			return;
		}
		// Show Result
		this.jTextArea.setText("");
		this.jTextFieldObligatorias.setText("");
		sortPairList(ausentes);
		showResident(res.toString(), ausentes, obligatorios);
		saveAusentes();
    }//GEN-LAST:event_jButtonAddAusenciasActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
		this.jTextArea.setText("");
		String name = String.valueOf(this.jComboBox2.getSelectedItem());
		if (!(name.length() == 0)) {
			this.jTextFieldAusencias.setText("");
			this.jTextFieldObligatorias.setText("");
			showResident(name, ausentes, obligatorios);
		}
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextFieldMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMonthActionPerformed

    }//GEN-LAST:event_jTextFieldMonthActionPerformed

    private void jButtonRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestartActionPerformed
		ausentes.clear();
		obligatorios.clear();
		calendarioActual = null;
		this.jTextArea.setText("");
		showResidents(residentes, ausentes, obligatorios);
    }//GEN-LAST:event_jButtonRestartActionPerformed

    private void jButtonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHelpActionPerformed
		this.jTextArea.setText(HELP);
    }//GEN-LAST:event_jButtonHelpActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
		saveResis();
		saveAusentes();
		saveObligatorios();
		System.exit(0);
		//this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jTextFieldYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldYearActionPerformed

    }//GEN-LAST:event_jTextFieldYearActionPerformed

    private void jButtonResidentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResidentesActionPerformed
		jTextArea.setText("");
		showResidents(residentes, ausentes, obligatorios);
    }//GEN-LAST:event_jButtonResidentesActionPerformed

    private void jButtonRmvAusenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRmvAusenciasActionPerformed
		// Get Text
		String _ausencias = this.jTextFieldAusencias.getText();
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		// Get Res
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		Residente res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
		if (!removeResFrom(ausentes, _ausencias, res)) {
			return;
		}
		// Show Result
		this.jTextArea.setText("");
		this.jTextFieldObligatorias.setText("");
		sortPairList(ausentes);
		showResident(res.toString(), ausentes, obligatorios);
		saveAusentes();
    }//GEN-LAST:event_jButtonRmvAusenciasActionPerformed

    private void jButtonAddObligatoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddObligatoriasActionPerformed
		// Get Text
		String _obligatorias = this.jTextFieldObligatorias.getText();
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		// Get Res
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		Residente res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
		if (!addResTo(obligatorios, _obligatorias, res)) {
			return;
		}
		// Show Result
		this.jTextArea.setText("");
		this.jTextFieldObligatorias.setText("");
		sortPairList(obligatorios);
		showResident(res.toString(), ausentes, obligatorios);
		saveObligatorios();
    }//GEN-LAST:event_jButtonAddObligatoriasActionPerformed

	private boolean addResTo(List<Pair<Integer, Residente>> list, String str, Residente res) {
		String[] _obligatorias_split = str.split(",| ");
		int dia;
		int dia1;
		int dia2;
		String s_err = "";
		try {
			for (String s : _obligatorias_split) {
				s_err = s;
				if (s.equals("")) {
					continue;
				}
				// Si es un rango de valores
				if (s.contains("-")) {
					// Cogemos ambos valores
					String[] _dayset = s.split("-");
					if (_dayset.length > 2) {
						throw new Exception();
					}
					dia1 = Integer.parseInt(_dayset[0]);
					dia2 = Integer.parseInt(_dayset[1]);
					if (dia1 < 1 || dia1 > 31 || dia2 < 1 || dia2 > 31 ) {
						throw new Exception();
					}
					// Vamos del dia1 al dia2
					if (dia1 > dia2) {
						int aux = dia2;
						dia2 = dia1;
						dia1 = aux;
					}
					// Agregamos obligatorios
					for (int i = dia1; i <= dia2; i++) {
						Pair<Integer, Residente> aus = new Pair(i - 1, res);
						boolean has = list.contains(aus);
						if (!has) {
							list.add(aus);
						} else {
							System.err.println("\r\n # Error. " + res.getName() + " ya está añadida al día " + i);
						}
					}
				// Si es un valor concreto
				} else {
					dia = Integer.parseInt(s);
					if (dia < 1 || dia > 31) {
						throw new Exception();
					} else {
						Pair<Integer, Residente> aus = new Pair(dia - 1, res);
						boolean has = list.contains(aus);
						if (!has) {
							list.add(aus);
						} else {
							System.err.println("\r\n # Error. " + res.getName() + " ya está añadida al día " + dia);
						}
					}
				}
			}
		} catch (Exception exc) {
			System.err.println("\r\n # Error en textArea: " + s_err);
			return false;
		}
		return true;
	}
	
	private boolean removeResFrom(List<Pair<Integer, Residente>> list, String str, Residente res) {
		String[] _obligatorias_split = str.split(",| ");
		int dia;
		int dia1;
		int dia2;
		String s_err = "";
		try {
			for (String s : _obligatorias_split) {
				s_err = s;
				if (s.equals("")) {
					continue;
				}
				// Si es un rango de valores
				if (s.contains("-")) {
					// Cogemos ambos valores
					String[] _dayset = s.split("-");
					if (_dayset.length > 2) {
						throw new Exception();
					}
					dia1 = Integer.parseInt(_dayset[0]);
					dia2 = Integer.parseInt(_dayset[1]);
					if (dia1 < 1 || dia1 > 31 || dia2 < 1 || dia2 > 31 ) {
						throw new Exception();
					}
					// Vamos del dia1 al dia2
					if (dia1 > dia2) {
						int aux = dia2;
						dia2 = dia1;
						dia1 = aux;
					}
					// Agregamos obligatorios
					for (int i = dia1; i <= dia2; i++) {
						Pair<Integer, Residente> aus = new Pair(i - 1, res);
						boolean has = list.contains(aus);
						if (has) {
							list.remove(aus);
						} else {
							System.err.println("\r\n # Error. " + res.getName() + " no está añadida al día " + i);
						}
					}
				// Si es un valor concreto
				} else {
					dia = Integer.parseInt(s);
					if (dia < 1 || dia > 31) {
						throw new Exception();
					} else {
						Pair<Integer, Residente> aus = new Pair(dia - 1, res);
						boolean has = list.contains(aus);
						if (has) {
							list.remove(aus);
						} else {
							System.err.println("\r\n # Error. " + res.getName() + " no está añadida al día " + dia);
						}
					}
				}
			}
		} catch (Exception exc) {
			System.err.println("\r\n # Error en textArea: " + s_err);
			return false;
		}
		return true;
	}
	
    private void jButtonRmvObligatoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRmvObligatoriasActionPerformed
		// Get Text
		String _obligatorias = this.jTextFieldObligatorias.getText();
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		// Get Res
		if (this.jComboBox2.getSelectedItem() == "") {
			System.err.println("\r\n # Error en campo Residente: no se ha seleccionado a nadie");
			return;
		}
		Residente res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
		if (removeResFrom(obligatorios, _obligatorias, res)) {
			this.jTextArea.setText("");
			showResident(res.toString(), ausentes, obligatorios);
		}
		// Show Result
		this.jTextFieldObligatorias.setText("");
		sortPairList(obligatorios);
		saveObligatorios();
    }//GEN-LAST:event_jButtonRmvObligatoriasActionPerformed

    private void jButtonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCleanActionPerformed
		this.jTextArea.setText("");
    }//GEN-LAST:event_jButtonCleanActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
		if (!(jTextArea.getText().length() == 0)) {
			JFileChooser chooser = new JFileChooser();
			if (calendarioActual != null) {
				chooser.setSelectedFile(new File("guardias" + calendarioActual.getId()));
			}
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				try (FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".txt")) {
					fw.write(this.jTextArea.getText());
					fw.close();
				} catch (IOException ex) {
					Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else {
			System.err.println("\r\n # Error: no se pudo exportar a txt.");
		}
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void ResEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResEditButtonActionPerformed

    }//GEN-LAST:event_ResEditButtonActionPerformed

    private void ResEditButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResEditButtonMouseClicked
		if (resListOpen) {
			return;
		}
		resListOpen = true;
		ListaResidentes lr = new ListaResidentes(residentes);
		lr.setVisible(true);
		lr.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent windowEvent) {
				resListOpen = false;
				// Actualizamos el jComboBox2
				jComboBox2.removeAllItems();
				jComboBox2.addItem("");
				for (Residente res : residentes.values()) {
					jComboBox2.addItem(res.toString());
				}
				// Si se ha eliminado un residente, se elimina de ausentes
				List<Pair<Integer, Residente>> to_remove = new ArrayList();
				for (Pair<Integer, Residente> aus : ausentes) {
					Residente res = aus.getValue();
					if (!residentes.containsKey(res.getId())) {
						to_remove.add(aus);
					}
				}
				ausentes.removeAll(to_remove);
				// Si se ha eliminado un residente, se elimina de obligatorios
				to_remove.clear();
				for (Pair<Integer, Residente> obl : obligatorios) {
					Residente res = obl.getValue();
					if (!residentes.containsKey(res.getId())) {
						to_remove.add(obl);
					}
				}
				obligatorios.removeAll(to_remove);
				to_remove.clear();
				jTextArea.setText("");
				saveResis();
				showResidents(residentes, ausentes, obligatorios);
			}
		});
    }//GEN-LAST:event_ResEditButtonMouseClicked
	// </editor-fold>

	/**
	 * @param args the command line arguments
	 */
	// <editor-fold defaultstate="collapse" desc="Variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ResEditButton;
    private javax.swing.JButton jButtonAddAusencias;
    private javax.swing.JButton jButtonAddObligatorias;
    private javax.swing.JButton jButtonClean;
    private javax.swing.JButton jButtonCrearCalendario;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonHelp;
    private javax.swing.JButton jButtonResidentes;
    private javax.swing.JButton jButtonRestart;
    private javax.swing.JButton jButtonRmvAusencias;
    private javax.swing.JButton jButtonRmvObligatorias;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabelAusencias;
    private javax.swing.JLabel jLabelGuardias;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelObligatorias;
    private javax.swing.JLabel jLabelResidente;
    private javax.swing.JLabel jLabelSeed;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldAusencias;
    private javax.swing.JTextField jTextFieldMonth;
    private javax.swing.JTextField jTextFieldObligatorias;
    private javax.swing.JTextField jTextFieldSeed;
    private javax.swing.JTextField jTextFieldYear;
    // End of variables declaration//GEN-END:variables
	// </editor-fold>
}
