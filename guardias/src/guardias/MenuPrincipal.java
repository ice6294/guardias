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
import static guardias.Utils.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @version v1.0
 * @author luis
 */
public class MenuPrincipal extends javax.swing.JFrame {

	public int year = 2016;
	public int month = 3;
	public int seed = 360;

	public Calendario calendarioActual;

	public List<Residente> residentes = new ArrayList();

	public List<Pair<Integer, Residente>> ausentes = new ArrayList();
	public List<Pair<Integer, Residente>> obligatorios = new ArrayList();

	/**
	 * Creates new form MenuPrincipal
	 */
	public MenuPrincipal() {
		initComponents();
		// <editor-fold desc="// Inicializar residentes">
		Residente res0 = new Residente("Elena", 0); // R5
		Residente res1 = new Residente("Natalia", 1); // R5
		Residente res2 = new Residente("Javi", 2); // R4
		Residente res3 = new Residente("Pablo", 3); // R4
		Residente res4 = new Residente("Rosaura", 4); // R3
		Residente res5 = new Residente("Joaquin", 5); // R3
		Residente res6 = new Residente("Dani", 6); // R2
		Residente res7 = new Residente("Pau", 7); // R2
		Residente res8 = new Residente("Laura", 8); // R1
		Residente res9 = new Residente("Carmen", 9); // R1
		// </editor-fold>

		// <editor-fold desc="// Agregamos lista de residentes">
		residentes.add(res0);
		residentes.add(res1);
		residentes.add(res2);
		residentes.add(res3);
		residentes.add(res4);
		residentes.add(res5);
		residentes.add(res6);
		residentes.add(res7);
		residentes.add(res8);
		residentes.add(res9);
		// </editor-fold>

		// <editor-fold desc="// Agregamos lista de residentes">
		this.jComboBox2.addItem("");
		this.jComboBox2.addItem(res0.toString());
		this.jComboBox2.addItem(res1.toString());
		this.jComboBox2.addItem(res2.toString());
		this.jComboBox2.addItem(res3.toString());
		this.jComboBox2.addItem(res4.toString());
		this.jComboBox2.addItem(res5.toString());
		this.jComboBox2.addItem(res6.toString());
		this.jComboBox2.addItem(res7.toString());
		this.jComboBox2.addItem(res8.toString());
		this.jComboBox2.addItem(res9.toString());
		// </editor-fold>

		CTextAreaOutputStream taos = new CTextAreaOutputStream();

		// Redirijimos las salidas
		System.setOut(new PrintStream(taos, true) {
			@Override
			public synchronized void println(String s) {
				if ((s != null) && (s.length() > 0)) {
					jTextArea.setText(jTextArea.getText() + "\r\n" + s);
				}
			}

			@Override
			public synchronized void print(String s) {
				if ((s != null) && (s.length() > 0)) {
					jTextArea.setText(jTextArea.getText() + s);
				}
			}
		});

		System.setErr(new PrintStream(taos, true) {
			@Override
			public synchronized void println(String s) {
				if ((s != null) && (s.length() > 0)) {
					jTextArea.setText(jTextArea.getText() + "\r\n" + s);
				}
			}

			@Override
			public synchronized void print(String s) {
				if ((s != null) && (s.length() > 0)) {
					jTextArea.setText(jTextArea.getText() + s);
				}
			}
		});

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guardias - Residentes (CGD) - Puerta del Hierro");

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
                                .addComponent(jButtonRestart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(jLabelResidente, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                        .addGap(26, 26, 26))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelObligatorias, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jButtonResidentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGuardias)
                    .addComponent(jButtonResidentes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(810, 719));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
					calendarioActual = cthulhu(year, month, seed, residentes, ausentes, obligatorios);
				}
			}, "Chtulhu");
			final Thread wait = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						/*
						for (int i = 1; i < 11; i++) {
							if (th.isAlive() && i == 1) {
								System.out.println(" Asignando ...\r\n");
							}
							Thread.sleep(1000);
							if (th.isAlive()) {
								System.out.println("  " + i + " ...\r\n");
							}
						}*/
						Thread.sleep(10000);
						if (th.isAlive()) {
							System.err.println(" # Error: Imposible esta combinación. Pruebe con otras condiciones u otra seed.");
							th.interrupt();
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
		String _ausencias = this.jTextFieldAusencias.getText();
		String[] _ausencias_split = _ausencias.split(", |,| ");
		for (String s : _ausencias_split) {
			int dia;
			try {
				dia = Integer.parseInt(s);
				if (dia < 1 || dia > 31) {
					throw new Exception();
				} else {
					Residente res;
					if (this.jComboBox2.getSelectedItem() == "") {
						throw new Exception();
					}
					res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
					Pair<Integer, Residente> aus = new Pair(dia - 1, res);
					if (!ausentes.contains(aus)) {
						ausentes.add(aus);
					} else {
						System.err.println("\r\n # Error. " + res.getName() + " ya está añadida al día " + dia);
					}
					this.jTextArea.setText("");
					this.jTextFieldAusencias.setText("");
					sortPairList(ausentes);
					showResident(res.toString(), ausentes, obligatorios);
				}
			} catch (Exception exc) {
				System.err.println("\r\n# Error en campo AUSENCIAS: " + s);
			}
		}
    }//GEN-LAST:event_jButtonAddAusenciasActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
		this.jTextArea.setText("");
		String name = String.valueOf(this.jComboBox2.getSelectedItem());
		if (!"".equals(name)) {
			this.jTextFieldAusencias.setText("");
			this.jTextFieldObligatorias.setText("");
			showResident(name, ausentes, obligatorios);
		} else {
		}
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextFieldMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMonthActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMonthActionPerformed

    private void jButtonRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestartActionPerformed
		ausentes.clear();
		obligatorios.clear();
		calendarioActual = null;
		this.jTextArea.setText("");
    }//GEN-LAST:event_jButtonRestartActionPerformed

    private void jButtonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHelpActionPerformed
		this.jTextArea.setText(HELP);
    }//GEN-LAST:event_jButtonHelpActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
		System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jTextFieldYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldYearActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldYearActionPerformed

    private void jButtonResidentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResidentesActionPerformed
		jTextArea.setText("");
		showResidents(residentes, ausentes, obligatorios);
    }//GEN-LAST:event_jButtonResidentesActionPerformed

    private void jButtonRmvAusenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRmvAusenciasActionPerformed
		String _ausencias = this.jTextFieldAusencias.getText();
		String[] _ausencias_split = _ausencias.split(", |,| ");
		for (String s : _ausencias_split) {
			int dia;
			try {
				dia = Integer.parseInt(s);
				if (dia < 1 || dia > 31) {
					throw new Exception();
				} else {
					Residente res;
					if (this.jComboBox2.getSelectedItem() == "") {
						throw new Exception();
					}
					res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
					Pair<Integer, Residente> aus = new Pair(dia - 1, res);
					if (ausentes.contains(aus)) {
						ausentes.remove(aus);
					} else {
						System.err.println("\r\n # Error. " + res.getName() + " no está añadida al día " + dia);
					}
					this.jTextArea.setText("");
					this.jTextFieldAusencias.setText("");
					sortPairList(ausentes);
					showResident(res.toString(), ausentes, obligatorios);
				}
			} catch (Exception exc) {
				System.err.println("\r\n # Error en campo AUSENCIAS: " + s);
			}
		}
    }//GEN-LAST:event_jButtonRmvAusenciasActionPerformed

    private void jButtonAddObligatoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddObligatoriasActionPerformed
		String _obligatorias = this.jTextFieldObligatorias.getText();
		String[] _obligatorias_split = _obligatorias.split(", |,| ");
		for (String s : _obligatorias_split) {
			int dia;
			try {
				dia = Integer.parseInt(s);
				if (dia < 0 || dia > 31) {
					throw new Exception();
				} else {
					Residente res;
					if (this.jComboBox2.getSelectedItem() == "") {
						throw new Exception();
					}
					res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
					Pair<Integer, Residente> aus = new Pair(dia - 1, res);
					if (!obligatorios.contains(aus)) {
						obligatorios.add(aus);
					} else {
						System.err.println("\r\n # Error. " + res.getName() + " ya está añadida al día " + dia);
					}
					this.jTextArea.setText("");
					this.jTextFieldObligatorias.setText("");
					sortPairList(obligatorios);
					showResident(res.toString(), ausentes, obligatorios);
				}
			} catch (Exception exc) {
				System.err.println("\r\n # Error en campo AUSENCIAS: " + s);
			}
		}
    }//GEN-LAST:event_jButtonAddObligatoriasActionPerformed

    private void jButtonRmvObligatoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRmvObligatoriasActionPerformed
		String _obligatorias = this.jTextFieldObligatorias.getText();
		String[] _obligatorias_split = _obligatorias.split(", |,| ");
		for (String s : _obligatorias_split) {
			int dia;
			try {
				dia = Integer.parseInt(s);
				if (dia < 0 || dia > 31) {
					throw new Exception();
				} else {
					Residente res;
					if (this.jComboBox2.getSelectedItem() == "") {
						throw new Exception();
					}
					res = getResidentFromToString(residentes, this.jComboBox2.getSelectedItem().toString());
					Pair<Integer, Residente> aus = new Pair(dia - 1, res);
					if (obligatorios.contains(aus)) {
						obligatorios.remove(aus);
					} else {
						System.err.println("\r\n # Error. " + res.getName() + " no está añadida al día " + dia);
					}
					this.jTextArea.setText("");
					this.jTextFieldObligatorias.setText("");
					sortPairList(ausentes);
					showResident(res.toString(), ausentes, obligatorios);
				}
			} catch (Exception exc) {
				System.err.println("\r\n # Error en campo AUSENCIAS: " + s);
			}
		}
    }//GEN-LAST:event_jButtonRmvObligatoriasActionPerformed

    private void jButtonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCleanActionPerformed
		this.jTextArea.setText("");
    }//GEN-LAST:event_jButtonCleanActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
		if (!"".equals(jTextArea.getText())) {
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
	/**
	 * @param args the command line arguments
	 */
	// <editor-fold defaultstate="collapse" desc="Variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
