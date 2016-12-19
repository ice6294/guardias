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
package jsonsmcreator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luis
 * @date 12-12-2016
 */
public class CalendarMenu extends javax.swing.JFrame {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private final Module module;
	private final Calendar calendar;
	
	private Integer fixResID;
	private Integer dayID;
	private Integer assignID;
	private Integer exceptionID;
	private Integer petitionID;
	
	
	private List<String> months;
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public CalendarMenu(Module module) {
		this.module = module;
		this.calendar = new Calendar();
		this.fixResID = 0;
		this.dayID = 0;
		this.assignID = 0;
		this.exceptionID = 0;
		this.petitionID = 0;
		initComponents();
		this.setMonths();
	}
	// </editor-fold>
	
	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	public final void setMonths() {
		this.months = new ArrayList();
		this.months.add("Enero");
		this.months.add("Febrero");
		this.months.add("Marzo");
		this.months.add("Abril");
		this.months.add("Mayo");
		this.months.add("Junio");
		this.months.add("Julio");
		this.months.add("Agosto");
		this.months.add("Septiembre");
		this.months.add("Octubre");
		this.months.add("Noviembre");
		this.months.add("Diciembre");
		this.CalendarComboBoxMONTH.removeAllItems();
		for (String month : this.months) {
			this.CalendarComboBoxMONTH.addItem(month);
		}
	}
	
	public void setFixResID() {
		String str = this.CalendarTextFieldFIXRESID.getText();
		if (!str.equals(""))
			this.fixResID = Integer.parseInt(str);
		else
			this.fixResID = 0;
	}
	
	public void setDayID() {
		String str = this.CalendarTextFieldDAYID.getText();
		if (!str.equals(""))
			this.dayID = Integer.parseInt(str);
		else
			this.dayID = 0;
	}
	
	public void setAssignID() {
		String str = this.CalendarTextFieldASSIGNID.getText();
		if (!str.equals(""))
			this.assignID = Integer.parseInt(str);
		else
			this.assignID = 0;
	}
	
	public void setExceptionID() {
		String str = this.CalendarTextFieldEXCEPTIONID.getText();
		if (!str.equals(""))
			this.exceptionID = Integer.parseInt(str);
		else
			this.exceptionID = 0;
	}
	
	public void setPetitionID() {
		String str = this.CalendarTextFieldPETITIONID.getText();
		if (!str.equals(""))
			this.petitionID = Integer.parseInt(str);
		else
			this.petitionID = 0;
	}
	
	public void setIDs() {
		this.setFixResID();
		this.setDayID();
		this.setAssignID();
		this.setExceptionID();
		this.setPetitionID();
	}
	
	public String getDate(Integer day) {
		return (this.CalendarTextFieldYEAR.getText() + "-" + (this.CalendarComboBoxMONTH.getSelectedIndex() + 1) + "-" + (day + 1));
	}
	
	public List<Assign> getAssigns(Integer i) {
		List<Assign> assigns = new ArrayList();
		String asgStr = (String) this.CalendarTable.getValueAt(i, 1);
		if (asgStr == null)
			return assigns;
		List<String> asgArray = Arrays.asList(asgStr.split(","));
		for (String str : asgArray) {
			List<String> asg = Arrays.asList(str.split(":"));
			if (asg.size() != 2)
				continue;
			Assign assign = new Assign(this.assignID, Integer.parseInt(asg.get(0)), Integer.parseInt(asg.get(1)));
			this.assignID++;
			assigns.add(assign);
		}
		return assigns;
	}
	
	public List<Exception> getExceptions(Integer i) {
		List<Exception> exceptions = new ArrayList();
		String excStr = (String) this.CalendarTable.getValueAt(i, 2);
		if (excStr == null)
			return exceptions;
		List<String> excArray = Arrays.asList(excStr.split(","));
		for (String str : excArray) {
			Exception exception = new Exception(this.exceptionID, Integer.parseInt(str));
			this.exceptionID++;
			exceptions.add(exception);
		}
		return exceptions;
	}
	
	public List<Petition> getPetitions(Integer i) {
		List<Petition> petitions = new ArrayList();
		String petStr = (String) this.CalendarTable.getValueAt(i, 3);
		if (petStr == null)
			return petitions;
		List<String> petArray = Arrays.asList(petStr.split(","));
		for (String str : petArray) {
			List<String> pet = Arrays.asList(str.split(":"));
			if (pet.size() != 2)
				continue;
			Petition petition = new Petition(this.petitionID, Integer.parseInt(pet.get(0)), Integer.parseInt(pet.get(1)));
			this.petitionID++;
			petitions.add(petition);
		}
		return petitions;
	}
	
	public Calendar getCalendar() {
		this.setCalendar();
		return this.calendar;
	}

	public void setCalendar() {
		this.setIDs();
		// Basic
		this.calendar.setId(Integer.parseInt(this.CalendarTextFieldID.getText()));
		this.calendar.setYear(Integer.parseInt(this.CalendarTextFieldYEAR.getText()));
		this.calendar.setMonth(this.CalendarComboBoxMONTH.getSelectedIndex()); // TODO: check!!!
		this.calendar.setSeed(Integer.parseInt(this.CalendarTextFieldSEED.getText()));
		// Days
		for (int i = 0; i < this.CalendarTable.getRowCount(); i++) {
			if (this.CalendarTable.getValueAt(i, 1) == null) {
				continue;
			}
			Day day = new Day();
			day.setId(this.dayID);
			day.setDate(this.getDate(i));
			day.setAssigns(this.getAssigns(i));
			day.setExceptions(this.getExceptions(i));
			day.setPetitions(this.getPetitions(i));
			this.calendar.getDays().add(day);
			this.dayID++;
		}
		// Rules
		for (Member member : this.module.getMembers()) {
			FixRes res = new FixRes(this.fixResID, member.getRole(), member.getUserId());
			this.fixResID++;
			this.calendar.getResidents().add(res);
		}
	}
	// </editor-fold>

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CalendarInfo = new javax.swing.JPanel();
        CalendarTitle = new javax.swing.JLabel();
        MemberLabelID = new javax.swing.JLabel();
        DayLabelID = new javax.swing.JLabel();
        CalendarTextFieldFIXRESID = new javax.swing.JTextField();
        CalendarTextFieldDAYID = new javax.swing.JTextField();
        AssignLabelID = new javax.swing.JLabel();
        ExceptionLabelID = new javax.swing.JLabel();
        CalendarTextFieldEXCEPTIONID = new javax.swing.JTextField();
        CalendarTextFieldASSIGNID = new javax.swing.JTextField();
        PetitionLabelID = new javax.swing.JLabel();
        CalendarTextFieldPETITIONID = new javax.swing.JTextField();
        YearLabel = new javax.swing.JLabel();
        CalendarTextFieldYEAR = new javax.swing.JTextField();
        MonthLabel = new javax.swing.JLabel();
        SeedLabel = new javax.swing.JLabel();
        CalendarTextFieldSEED = new javax.swing.JTextField();
        toJSONButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JButton();
        CalendarCheckBoxFIXED = new javax.swing.JCheckBox();
        CalendarComboBoxMONTH = new javax.swing.JComboBox<>();
        CalendarTextFieldID = new javax.swing.JTextField();
        IDLabel = new javax.swing.JLabel();
        LoadButton = new javax.swing.JButton();
        Calendar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CalendarTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CalendarInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CalendarTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        CalendarTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CalendarTitle.setText("CALENDAR");

        MemberLabelID.setText("1º FixRes Id");

        DayLabelID.setText("1º Day Id");

        AssignLabelID.setText("1º Assign Id");

        ExceptionLabelID.setText("1º Exception Id");

        PetitionLabelID.setText("1º Petition Id");

        YearLabel.setText("YEAR");

        MonthLabel.setText("MONTH");

        SeedLabel.setText("SEED");

        toJSONButton.setText("toJSON");
        toJSONButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toJSONButtonActionPerformed(evt);
            }
        });

        CloseButton.setText("close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        CalendarCheckBoxFIXED.setText("FIX");

        CalendarComboBoxMONTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CalendarComboBoxMONTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalendarComboBoxMONTHActionPerformed(evt);
            }
        });

        IDLabel.setText("ID");

        LoadButton.setText("load");
        LoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CalendarInfoLayout = new javax.swing.GroupLayout(CalendarInfo);
        CalendarInfo.setLayout(CalendarInfoLayout);
        CalendarInfoLayout.setHorizontalGroup(
            CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalendarInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CalendarTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MemberLabelID)
                    .addComponent(DayLabelID, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(CalendarInfoLayout.createSequentialGroup()
                        .addComponent(CalendarTextFieldDAYID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ExceptionLabelID))
                    .addGroup(CalendarInfoLayout.createSequentialGroup()
                        .addComponent(CalendarTextFieldFIXRESID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AssignLabelID)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CalendarTextFieldASSIGNID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalendarTextFieldEXCEPTIONID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CalendarInfoLayout.createSequentialGroup()
                        .addComponent(PetitionLabelID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarTextFieldPETITIONID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LoadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toJSONButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CloseButton))
                    .addGroup(CalendarInfoLayout.createSequentialGroup()
                        .addGap(0, 43, Short.MAX_VALUE)
                        .addComponent(IDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(YearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarTextFieldYEAR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MonthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarComboBoxMONTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarTextFieldSEED, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarCheckBoxFIXED)))
                .addContainerGap())
        );
        CalendarInfoLayout.setVerticalGroup(
            CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalendarInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CalendarInfoLayout.createSequentialGroup()
                        .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MemberLabelID)
                            .addComponent(CalendarTextFieldFIXRESID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AssignLabelID)
                            .addComponent(CalendarTextFieldASSIGNID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PetitionLabelID)
                            .addComponent(CalendarTextFieldPETITIONID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toJSONButton)
                            .addComponent(CloseButton)
                            .addComponent(LoadButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CalendarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DayLabelID)
                            .addComponent(CalendarTextFieldDAYID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ExceptionLabelID)
                            .addComponent(CalendarTextFieldEXCEPTIONID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SeedLabel)
                            .addComponent(CalendarTextFieldSEED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MonthLabel)
                            .addComponent(CalendarTextFieldYEAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(YearLabel)
                            .addComponent(CalendarCheckBoxFIXED)
                            .addComponent(CalendarComboBoxMONTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDLabel)
                            .addComponent(CalendarTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(CalendarTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Calendar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CalendarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null},
                { new Integer(2), null, null, null},
                { new Integer(3), null, null, null},
                { new Integer(4), null, null, null},
                { new Integer(5), null, null, null},
                { new Integer(6), null, null, null},
                { new Integer(7), null, null, null},
                { new Integer(8), null, null, null},
                { new Integer(9), null, null, null},
                { new Integer(10), null, null, null},
                { new Integer(11), null, null, null},
                { new Integer(12), null, null, null},
                { new Integer(13), null, null, null},
                { new Integer(14), null, null, null},
                { new Integer(15), null, null, null},
                { new Integer(16), null, null, null},
                { new Integer(17), null, null, null},
                { new Integer(18), null, null, null},
                { new Integer(19), null, null, null},
                { new Integer(20), null, null, null},
                { new Integer(21), null, null, null},
                { new Integer(22), null, null, null},
                { new Integer(23), null, null, null},
                { new Integer(24), null, null, null},
                { new Integer(25), null, null, null},
                { new Integer(26), null, null, null},
                { new Integer(27), null, null, null},
                { new Integer(28), null, null, null},
                { new Integer(29), null, null, null},
                { new Integer(30), null, null, null},
                { new Integer(31), null, null, null}
            },
            new String [] {
                "Día", "Assigns (ej. \"asgID:userID, asgID:userID ...\")", "Exceptions (ej. \"userID, userID ...\")", "Petitions (ej. \"petID:userID, petID:userID ...\")"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CalendarTable);
        if (CalendarTable.getColumnModel().getColumnCount() > 0) {
            CalendarTable.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout CalendarLayout = new javax.swing.GroupLayout(Calendar);
        Calendar.setLayout(CalendarLayout);
        CalendarLayout.setHorizontalGroup(
            CalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalendarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        CalendarLayout.setVerticalGroup(
            CalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalendarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CalendarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CalendarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void toJSONButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toJSONButtonActionPerformed
		String path = Utils.selectPathJSON();
		if (path.equals("")) {
			return;
		}
		try (FileWriter fw = new FileWriter(path)) {
			fw.write(this.getCalendar().toJSON());
			fw.close();
		} catch (IOException ex) {
			System.out.println("\r\n # Error al guardar calendar JSON (" + ex + ")");
		}
    }//GEN-LAST:event_toJSONButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void CalendarComboBoxMONTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalendarComboBoxMONTHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CalendarComboBoxMONTHActionPerformed

    private void LoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoadButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	//public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 *//*
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(CalendarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CalendarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CalendarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CalendarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}*/
		//</editor-fold>

		/* Create and display the form *//*
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CalendarMenu().setVisible(true);
			}
		});
	}*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AssignLabelID;
    private javax.swing.JPanel Calendar;
    private javax.swing.JCheckBox CalendarCheckBoxFIXED;
    private javax.swing.JComboBox<String> CalendarComboBoxMONTH;
    private javax.swing.JPanel CalendarInfo;
    private javax.swing.JTable CalendarTable;
    private javax.swing.JTextField CalendarTextFieldASSIGNID;
    private javax.swing.JTextField CalendarTextFieldDAYID;
    private javax.swing.JTextField CalendarTextFieldEXCEPTIONID;
    private javax.swing.JTextField CalendarTextFieldFIXRESID;
    private javax.swing.JTextField CalendarTextFieldID;
    private javax.swing.JTextField CalendarTextFieldPETITIONID;
    private javax.swing.JTextField CalendarTextFieldSEED;
    private javax.swing.JTextField CalendarTextFieldYEAR;
    private javax.swing.JLabel CalendarTitle;
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel DayLabelID;
    private javax.swing.JLabel ExceptionLabelID;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JButton LoadButton;
    private javax.swing.JLabel MemberLabelID;
    private javax.swing.JLabel MonthLabel;
    private javax.swing.JLabel PetitionLabelID;
    private javax.swing.JLabel SeedLabel;
    private javax.swing.JLabel YearLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton toJSONButton;
    // End of variables declaration//GEN-END:variables
}
