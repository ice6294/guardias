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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import javax.swing.JFileChooser;
//import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author luis
 * @date 12-12-2016
 */
public class ModuleMenu extends javax.swing.JFrame {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Module module;
	// </editor-fold>

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public ModuleMenu() {
		this.module = new Module();
		initComponents();
		this.DayTemplateCheckBox.setSelected(true);
	}
	// </editor-fold>

	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	public Module getModule() {
		this.setModule();
		return this.module;
	}

	public void setModule() {
		this.module = new Module();
		// Basic
		this.module.setId(Integer.parseInt(this.ModuleTextFieldID.getText()));
		this.module.setYear(Integer.parseInt(this.ModuleTextFieldYEAR.getText()));
		this.module.setName(this.ModuleTextFieldNAME.getText());
		this.module.setExtendedName(this.ModuleTextFieldEXTENDEDNAME.getText());
		this.module.setDescription(this.ModuleTextFieldDESCRIPTION.getText());
		this.module.setDayGroups(this.ModuleTextFieldDAYGROUPS.getText());
		// Hospital
		this.module.setHospitalId(Integer.parseInt(this.HospitalTextFieldID.getText()));
		this.module.setHospitalName(this.HospitalTextFieldNAME.getText());
		this.module.setHospitalLocation(this.HospitalTextFieldLOCATION.getText());
		// Admin (TODO: ???)
		// Members
		for (int i = 0; i < this.MembersTable.getRowCount(); i++) {
			if (this.MembersTable.getValueAt(i, 0) == null) {
				continue;
			}
			Member mem = new Member(
					(Integer) this.MembersTable.getValueAt(i, 0),
					(Boolean) this.MembersTable.getValueAt(i, 1),
					(Integer) this.MembersTable.getValueAt(i, 2),
					(Integer) this.MembersTable.getValueAt(i, 3),
					(Integer) this.MembersTable.getValueAt(i, 4),
					(String) this.MembersTable.getValueAt(i, 5),
					(String) this.MembersTable.getValueAt(i, 6),
					(String) this.MembersTable.getValueAt(i, 7),
					(String) this.MembersTable.getValueAt(i, 8),
					(String) this.MembersTable.getValueAt(i, 9)
			);
			this.module.getMembers().add(mem);
		}
		// Rules
		for (int i = 0; i < this.RulesTable.getRowCount(); i++) {
			if (this.RulesTable.getValueAt(i, 0) == null) {
				continue;
			}
			Rule rule = new Rule(
					(Integer) this.RulesTable.getValueAt(i, 0),
					(Integer) this.RulesTable.getValueAt(i, 1),
					(Boolean) this.RulesTable.getValueAt(i, 2),
					(String) this.RulesTable.getValueAt(i, 3)
			);
			this.module.getRules().add(rule);
		}
		// Jobs
		for (int i = 0; i < this.JobsTable.getRowCount(); i++) {
			if (this.JobsTable.getValueAt(i, 0) == null) {
				continue;
			}
			Job job = new Job(
					(Integer) this.JobsTable.getValueAt(i, 0),
					(String) this.JobsTable.getValueAt(i, 1),
					(String) this.JobsTable.getValueAt(i, 2),
					(String) this.JobsTable.getValueAt(i, 3)
			);
			this.module.getJobs().add(job);
		}
		// ResTypes
		for (int i = 0; i < this.ResTypesTable.getRowCount(); i++) {
			if (this.ResTypesTable.getValueAt(i, 0) == null) {
				continue;
			}
			ResType res = new ResType(
					(Integer) this.ResTypesTable.getValueAt(i, 0),
					(String) this.ResTypesTable.getValueAt(i, 1),
					(String) this.ResTypesTable.getValueAt(i, 2)
			);
			this.module.getResTypes().add(res);
		}
		// AssignType
		for (int i = 0; i < this.AssignTypesTable.getRowCount(); i++) {
			if (this.AssignTypesTable.getValueAt(i, 0) == null) {
				continue;
			}
			AssignType ast = new AssignType(
					(Integer) this.AssignTypesTable.getValueAt(i, 0),
					(Integer) this.AssignTypesTable.getValueAt(i, 1),
					(String) this.AssignTypesTable.getValueAt(i, 2),
					(String) this.AssignTypesTable.getValueAt(i, 3)
			);
			this.module.getAssignTypes().add(ast);
		}
		// PetitionType
		for (int i = 0; i < this.PetitionTypesTable.getRowCount(); i++) {
			if (this.PetitionTypesTable.getValueAt(i, 0) == null) {
				continue;
			}
			PetitionType pet = new PetitionType(
					(Integer) this.PetitionTypesTable.getValueAt(i, 0),
					(Integer) this.PetitionTypesTable.getValueAt(i, 1),
					(String) this.PetitionTypesTable.getValueAt(i, 2),
					(String) this.PetitionTypesTable.getValueAt(i, 3)
			);
			this.module.getPetitionTypes().add(pet);
		}
		// DayTemplate
		if (this.DayTemplateCheckBox.isSelected()) {
			if (this.TemplatesTable.getValueAt(0, 1) != null) {
				DayTemplate day = new DayTemplate(
						(Integer) this.TemplatesTable.getValueAt(0, 1),
						(String) this.TemplatesTable.getValueAt(0, 2)
				);
				this.module.setDayTemplate(day);
			}
		}
		// WeekTemplate
		else {
			WeekTemplate week = new WeekTemplate();
			for (int i = 1; i < this.TemplatesTable.getRowCount(); i++) {
				if (this.TemplatesTable.getValueAt(i, 1) == null) {
					continue;
				}
				DayTemplate weekDay = new DayTemplate(
						(Integer) this.TemplatesTable.getValueAt(i, 1),
						(String) this.TemplatesTable.getValueAt(i, 2)
				);
				week.getDayTemplates().add(weekDay);
			}
			this.module.setWeekTemplate(week);
		}
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public void loadModule() {
		//System.out.println("Module: " + this.module.toJSON());
		// Basic
		this.ModuleTextFieldID.setText(String.valueOf(this.module.getId()));
		this.ModuleTextFieldYEAR.setText(String.valueOf(this.module.getYear()));
		this.ModuleTextFieldNAME.setText(this.module.getName());
		this.ModuleTextFieldEXTENDEDNAME.setText(this.module.getExtendedName());
		this.ModuleTextFieldDESCRIPTION.setText(this.module.getDescription());
		this.ModuleTextFieldDAYGROUPS.setText(this.module.getDayGroups());
		// Hospital
		this.HospitalTextFieldID.setText(String.valueOf(this.module.getHospitalId()));
		this.HospitalTextFieldNAME.setText(this.module.getHospitalName());
		this.HospitalTextFieldLOCATION.setText(this.module.getHospitalLocation());
		// Admin (TODO: ???)
		// Member
		for (int i = 0; i < this.MembersTable.getRowCount() && i < this.module.getMembers().size(); i++) {
			Member mem = this.module.getMembers().get(i);
			this.MembersTable.setValueAt(mem.getId(), i, 0);
			this.MembersTable.setValueAt(mem.getMain(), i, 1);
			this.MembersTable.setValueAt(mem.getRole(), i, 2);
			this.MembersTable.setValueAt(mem.getUserId(), i, 3);
			this.MembersTable.setValueAt(mem.getUserYear(), i, 4);
			this.MembersTable.setValueAt(mem.getUserName(), i, 5);
			this.MembersTable.setValueAt(mem.getUserFirstName(), i, 6);
			this.MembersTable.setValueAt(mem.getUserLastName(), i, 7);
			this.MembersTable.setValueAt(mem.getUserEmail(), i, 8);
			this.MembersTable.setValueAt(mem.getUserSpeciality(), i, 9);
			
		}
		// Rules
		for (int i = 0; i < this.RulesTable.getRowCount() && i < this.module.getRules().size(); i++) {
			Rule rule = this.module.getRules().get(i);
			this.RulesTable.setValueAt(rule.getId(), i, 0);
			this.RulesTable.setValueAt(rule.getRank(), i, 1);
			this.RulesTable.setValueAt(rule.getActive(), i, 2);
			this.RulesTable.setValueAt(rule.getDescription(), i, 3);
		}
		// Jobs
		for (int i = 0; i < this.JobsTable.getRowCount() && i < this.module.getJobs().size(); i++) {
			Job job = this.module.getJobs().get(i);
			this.JobsTable.setValueAt(job.getId(), i, 0);
			this.JobsTable.setValueAt(job.getName(), i, 1);
			this.JobsTable.setValueAt(job.getColor(), i, 2);
			this.JobsTable.setValueAt(job.getBColor(), i, 3);
		}
		// ResTypes
		for (int i = 0; i < this.ResTypesTable.getRowCount() && i < this.module.getResTypes().size(); i++) {
			ResType res = this.module.getResTypes().get(i);
			this.ResTypesTable.setValueAt(res.getId(), i, 0);
			this.ResTypesTable.setValueAt(res.getName(), i, 1);
			this.ResTypesTable.setValueAt(res.getRoles(), i, 2);
		}
		// AssignType
		for (int i = 0; i < this.AssignTypesTable.getRowCount() && i < this.module.getAssignTypes().size(); i++) {
			AssignType ast = this.module.getAssignTypes().get(i);
			this.AssignTypesTable.setValueAt(ast.getId(), i, 0);
			this.AssignTypesTable.setValueAt(ast.getJobId(), i, 1);
			this.AssignTypesTable.setValueAt(ast.getResTypes(), i, 2);
			this.AssignTypesTable.setValueAt(ast.getRoles(), i, 3);
		}
		// PetitionType
		for (int i = 0; i < this.PetitionTypesTable.getRowCount() && i < this.module.getPetitionTypes().size(); i++) {
			PetitionType pet = this.module.getPetitionTypes().get(i);
			this.PetitionTypesTable.setValueAt(pet.getId(), i, 0);
			this.PetitionTypesTable.setValueAt(pet.getType(), i, 1);
			this.PetitionTypesTable.setValueAt(pet.getColor(), i, 2);
			this.PetitionTypesTable.setValueAt(pet.getBColor(), i, 3);
		}
		// DayTemplate
		DayTemplate day = this.module.getDayTemplate();
		this.TemplatesTable.setValueAt(day.getId(), 0, 1);
		this.TemplatesTable.setValueAt(day.getAssignTypes(), 0, 2);
		// WeekTemplate
		WeekTemplate week = this.module.getWeekTemplate();
		for (int i = 0; i < (this.TemplatesTable.getRowCount() - 1) &&  i < week.getDayTemplates().size(); i++) {
			DayTemplate weekDay = week.getDayTemplates().get(i);
			this.TemplatesTable.setValueAt(weekDay.getId(), i + 1, 1);
			this.TemplatesTable.setValueAt(weekDay.getAssignTypes(), i + 1, 2);
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

        ModuleINFO = new javax.swing.JPanel();
        ModuleLabelID = new javax.swing.JLabel();
        ModuleTextFieldID = new javax.swing.JTextField();
        ModuleTitle = new javax.swing.JLabel();
        ModuleLabelYEAR = new javax.swing.JLabel();
        ModuleTextFieldYEAR = new javax.swing.JTextField();
        ModuleLabelDESCRIPTION = new javax.swing.JLabel();
        ModuleTextFieldDESCRIPTION = new javax.swing.JTextField();
        ModuleTextFieldDAYGROUPS = new javax.swing.JTextField();
        ModuleLabelDAYGROUPS = new javax.swing.JLabel();
        ModuleTextFieldNAME = new javax.swing.JTextField();
        ModuleLabelNAME = new javax.swing.JLabel();
        ModuleLabelEXTENDEDNAME = new javax.swing.JLabel();
        ModuleTextFieldEXTENDEDNAME = new javax.swing.JTextField();
        Hospital = new javax.swing.JPanel();
        HospitalTitle = new javax.swing.JLabel();
        HospitalLabelID = new javax.swing.JLabel();
        HospitalTextFieldID = new javax.swing.JTextField();
        HospitalLabelNAME = new javax.swing.JLabel();
        HospitalTextFieldNAME = new javax.swing.JTextField();
        HospitalTextFieldLOCATION = new javax.swing.JTextField();
        HospitalLabelLOCATION = new javax.swing.JLabel();
        Members = new javax.swing.JPanel();
        MembersTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MembersTable = new javax.swing.JTable();
        ResTypes = new javax.swing.JPanel();
        ResTypesTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResTypesTable = new javax.swing.JTable();
        Jobs = new javax.swing.JPanel();
        JobsTitle = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JobsTable = new javax.swing.JTable();
        AssignTypes = new javax.swing.JPanel();
        AssignTypesTitle = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AssignTypesTable = new javax.swing.JTable();
        Templates = new javax.swing.JPanel();
        TemplatesTitle = new javax.swing.JLabel();
        DayTemplateCheckBox = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        TemplatesTable = new javax.swing.JTable();
        Rules = new javax.swing.JPanel();
        RulesTitle = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        RulesTable = new javax.swing.JTable();
        PetitionTypes = new javax.swing.JPanel();
        PetitionTypesTitle = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        PetitionTypesTable = new javax.swing.JTable();
        LoadButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        toJSONButton = new javax.swing.JButton();
        CreateCalendarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sched Mapper JSON Creator");
        setName("MODULE"); // NOI18N

        ModuleINFO.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ModuleINFO.setName("Module"); // NOI18N

        ModuleLabelID.setText("id");

        ModuleTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModuleTextFieldIDActionPerformed(evt);
            }
        });

        ModuleTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        ModuleTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModuleTitle.setText("Module");

        ModuleLabelYEAR.setText("year");

        ModuleTextFieldYEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModuleTextFieldYEARActionPerformed(evt);
            }
        });

        ModuleLabelDESCRIPTION.setText("description");

        ModuleTextFieldDESCRIPTION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModuleTextFieldDESCRIPTIONActionPerformed(evt);
            }
        });

        ModuleTextFieldDAYGROUPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModuleTextFieldDAYGROUPSActionPerformed(evt);
            }
        });

        ModuleLabelDAYGROUPS.setText("dayGroups");

        ModuleTextFieldNAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModuleTextFieldNAMEActionPerformed(evt);
            }
        });

        ModuleLabelNAME.setText("name");

        ModuleLabelEXTENDEDNAME.setText("extName");

        ModuleTextFieldEXTENDEDNAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModuleTextFieldEXTENDEDNAMEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ModuleINFOLayout = new javax.swing.GroupLayout(ModuleINFO);
        ModuleINFO.setLayout(ModuleINFOLayout);
        ModuleINFOLayout.setHorizontalGroup(
            ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModuleINFOLayout.createSequentialGroup()
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModuleINFOLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                                        .addComponent(ModuleLabelID)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ModuleTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                                        .addComponent(ModuleLabelYEAR)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ModuleTextFieldYEAR, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(ModuleTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                                .addComponent(ModuleLabelNAME)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ModuleTextFieldNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                                .addComponent(ModuleLabelEXTENDEDNAME)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ModuleTextFieldEXTENDEDNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(ModuleINFOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                        .addComponent(ModuleLabelDAYGROUPS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ModuleTextFieldDAYGROUPS, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModuleINFOLayout.createSequentialGroup()
                        .addComponent(ModuleLabelDESCRIPTION)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ModuleTextFieldDESCRIPTION, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ModuleINFOLayout.setVerticalGroup(
            ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModuleINFOLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(ModuleTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModuleLabelID)
                    .addComponent(ModuleTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModuleLabelYEAR)
                    .addComponent(ModuleTextFieldYEAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModuleLabelNAME)
                    .addComponent(ModuleTextFieldNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModuleLabelEXTENDEDNAME)
                    .addComponent(ModuleTextFieldEXTENDEDNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModuleLabelDESCRIPTION)
                    .addComponent(ModuleTextFieldDESCRIPTION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ModuleINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModuleLabelDAYGROUPS)
                    .addComponent(ModuleTextFieldDAYGROUPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Hospital.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Hospital.setName("Hospital"); // NOI18N

        HospitalTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        HospitalTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HospitalTitle.setText("Hospital");

        HospitalLabelID.setText("id");

        HospitalTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HospitalTextFieldIDActionPerformed(evt);
            }
        });

        HospitalLabelNAME.setText("name");

        HospitalTextFieldNAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HospitalTextFieldNAMEActionPerformed(evt);
            }
        });

        HospitalTextFieldLOCATION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HospitalTextFieldLOCATIONActionPerformed(evt);
            }
        });

        HospitalLabelLOCATION.setText("location");

        javax.swing.GroupLayout HospitalLayout = new javax.swing.GroupLayout(Hospital);
        Hospital.setLayout(HospitalLayout);
        HospitalLayout.setHorizontalGroup(
            HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HospitalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HospitalTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HospitalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HospitalLayout.createSequentialGroup()
                                .addComponent(HospitalLabelID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HospitalTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HospitalLayout.createSequentialGroup()
                                .addComponent(HospitalLabelNAME)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HospitalTextFieldNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HospitalLayout.createSequentialGroup()
                                .addComponent(HospitalLabelLOCATION)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HospitalTextFieldLOCATION, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        HospitalLayout.setVerticalGroup(
            HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HospitalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HospitalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HospitalLabelID)
                    .addComponent(HospitalTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HospitalLabelNAME)
                    .addComponent(HospitalTextFieldNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HospitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HospitalTextFieldLOCATION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HospitalLabelLOCATION))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Members.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Members.setName("Members"); // NOI18N

        MembersTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        MembersTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MembersTitle.setText("Members");

        MembersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Main", "Role", "User Id", "Year", "UserName", "First Name", "Last Name", "Email", "Speciality"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(MembersTable);

        javax.swing.GroupLayout MembersLayout = new javax.swing.GroupLayout(Members);
        Members.setLayout(MembersLayout);
        MembersLayout.setHorizontalGroup(
            MembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MembersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MembersTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MembersLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MembersLayout.setVerticalGroup(
            MembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MembersLayout.createSequentialGroup()
                .addComponent(MembersTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        ResTypes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ResTypes.setName("ResType"); // NOI18N

        ResTypesTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ResTypesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResTypesTitle.setText("ResTypes");

        ResTypesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Name", "Roles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ResTypesTable);
        if (ResTypesTable.getColumnModel().getColumnCount() > 0) {
            ResTypesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        javax.swing.GroupLayout ResTypesLayout = new javax.swing.GroupLayout(ResTypes);
        ResTypes.setLayout(ResTypesLayout);
        ResTypesLayout.setHorizontalGroup(
            ResTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ResTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(ResTypesTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ResTypesLayout.setVerticalGroup(
            ResTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ResTypesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Jobs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Jobs.setName("Jobs"); // NOI18N

        JobsTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        JobsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JobsTitle.setText("Jobs");

        JobsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Color", "BColor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(JobsTable);
        if (JobsTable.getColumnModel().getColumnCount() > 0) {
            JobsTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        javax.swing.GroupLayout JobsLayout = new javax.swing.GroupLayout(Jobs);
        Jobs.setLayout(JobsLayout);
        JobsLayout.setHorizontalGroup(
            JobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JobsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(JobsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JobsLayout.setVerticalGroup(
            JobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JobsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JobsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AssignTypes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AssignTypes.setName("AssignTypes"); // NOI18N

        AssignTypesTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        AssignTypesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssignTypesTitle.setText("AssignTypes");

        AssignTypesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Job Id", "ResType Ids", "Roles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(AssignTypesTable);
        if (AssignTypesTable.getColumnModel().getColumnCount() > 0) {
            AssignTypesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        javax.swing.GroupLayout AssignTypesLayout = new javax.swing.GroupLayout(AssignTypes);
        AssignTypes.setLayout(AssignTypesLayout);
        AssignTypesLayout.setHorizontalGroup(
            AssignTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssignTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AssignTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(AssignTypesTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AssignTypesLayout.setVerticalGroup(
            AssignTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssignTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AssignTypesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Templates.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TemplatesTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TemplatesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TemplatesTitle.setText("Templates");

        DayTemplateCheckBox.setText("Day Template");
        DayTemplateCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DayTemplateCheckBoxActionPerformed(evt);
            }
        });

        TemplatesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"All", null, null},
                {"Monday", null, null},
                {"Tuesday", null, null},
                {"Wednesday", null, null},
                {"Thursday", null, null},
                {"Friday", null, null},
                {"Saturday", null, null},
                {"Sunday", null, null}
            },
            new String [] {
                "WeekDay", "Id", "Assign Types Ids"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(TemplatesTable);
        if (TemplatesTable.getColumnModel().getColumnCount() > 0) {
            TemplatesTable.getColumnModel().getColumn(0).setMaxWidth(120);
            TemplatesTable.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout TemplatesLayout = new javax.swing.GroupLayout(Templates);
        Templates.setLayout(TemplatesLayout);
        TemplatesLayout.setHorizontalGroup(
            TemplatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TemplatesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TemplatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TemplatesLayout.createSequentialGroup()
                        .addComponent(TemplatesTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DayTemplateCheckBox))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addContainerGap())
        );
        TemplatesLayout.setVerticalGroup(
            TemplatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TemplatesLayout.createSequentialGroup()
                .addGroup(TemplatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TemplatesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DayTemplateCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Rules.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        RulesTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        RulesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RulesTitle.setText("Rules");

        RulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Rank", "Active", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(RulesTable);
        if (RulesTable.getColumnModel().getColumnCount() > 0) {
            RulesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        javax.swing.GroupLayout RulesLayout = new javax.swing.GroupLayout(Rules);
        Rules.setLayout(RulesLayout);
        RulesLayout.setHorizontalGroup(
            RulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RulesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RulesTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        RulesLayout.setVerticalGroup(
            RulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RulesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RulesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        PetitionTypes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PetitionTypesTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        PetitionTypesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PetitionTypesTitle.setText("PetitionTypes");

        PetitionTypesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Type", "Color", "BColor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(PetitionTypesTable);
        if (PetitionTypesTable.getColumnModel().getColumnCount() > 0) {
            PetitionTypesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        javax.swing.GroupLayout PetitionTypesLayout = new javax.swing.GroupLayout(PetitionTypes);
        PetitionTypes.setLayout(PetitionTypesLayout);
        PetitionTypesLayout.setHorizontalGroup(
            PetitionTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PetitionTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PetitionTypesTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PetitionTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PetitionTypesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        PetitionTypesLayout.setVerticalGroup(
            PetitionTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PetitionTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PetitionTypesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(PetitionTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PetitionTypesLayout.createSequentialGroup()
                    .addContainerGap(37, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        LoadButton.setText("Load");
        LoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        toJSONButton.setText("to JSON");
        toJSONButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toJSONButtonActionPerformed(evt);
            }
        });

        CreateCalendarButton.setText("Create Calendar");
        CreateCalendarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateCalendarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModuleINFO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Hospital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Jobs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SaveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LoadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toJSONButton))
                    .addComponent(CreateCalendarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ResTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AssignTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Members, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Templates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Rules, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PetitionTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Templates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Rules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Members, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ModuleINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Hospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ResTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Jobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(toJSONButton)
                                    .addComponent(LoadButton)
                                    .addComponent(SaveButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CreateCalendarButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(PetitionTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AssignTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModuleTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModuleTextFieldIDActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_ModuleTextFieldIDActionPerformed

    private void ModuleTextFieldYEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModuleTextFieldYEARActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_ModuleTextFieldYEARActionPerformed

    private void ModuleTextFieldDESCRIPTIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModuleTextFieldDESCRIPTIONActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_ModuleTextFieldDESCRIPTIONActionPerformed

    private void ModuleTextFieldDAYGROUPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModuleTextFieldDAYGROUPSActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_ModuleTextFieldDAYGROUPSActionPerformed

    private void HospitalTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HospitalTextFieldIDActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_HospitalTextFieldIDActionPerformed

    private void HospitalTextFieldNAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HospitalTextFieldNAMEActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_HospitalTextFieldNAMEActionPerformed

    private void HospitalTextFieldLOCATIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HospitalTextFieldLOCATIONActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_HospitalTextFieldLOCATIONActionPerformed

    private void DayTemplateCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DayTemplateCheckBoxActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_DayTemplateCheckBoxActionPerformed

    private void LoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadButtonActionPerformed
		String modulePath = Utils.selectPath();
		if (modulePath.equals("") || !modulePath.endsWith(".smc")) {
			return;
		}
		File f = new File(modulePath);
		// Si existe el fichero, cargamos los residentes del mismo
		if (f.exists()) {
			FileInputStream in;
			ObjectInputStream ois;
			try {
				in = new FileInputStream(modulePath);
				ois = new ObjectInputStream(in);
				this.module = (Module) ois.readObject();
				this.loadModule();
				ois.close();
			} catch (FileNotFoundException ex) {
				System.err.println("\r\n # Error al cargar residentes (" + ex + ")");
			} catch (IOException | ClassNotFoundException ex) {
				System.err.println("\r\n # Error al cargar residentes (" + ex + ")");
			}
		}
    }//GEN-LAST:event_LoadButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
		String modulePath = Utils.selectPathSMC();
		if (modulePath.equals("")) {
			return;
		}
		FileOutputStream out;
		ObjectOutputStream oos;
		try {
			out = new FileOutputStream(modulePath);
			oos = new ObjectOutputStream(out);
			oos.writeObject(this.getModule());
			oos.close();
		} catch (FileNotFoundException ex) {
			System.err.println("\r\n # Error al guardar módulo SMC (" + ex + ")");
		} catch (IOException ex) {
			System.err.println("\r\n # Error al guardar módulo SMC (" + ex + ")");
		}
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void toJSONButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toJSONButtonActionPerformed
		String path = Utils.selectPathJSON();
		if (path.equals("")) {
			return;
		}
		try (FileWriter fw = new FileWriter(path)) {
			fw.write(this.getModule().toJSON());
			fw.close();
		} catch (IOException ex) {
			System.out.println("\r\n # Error al guardar módulo JSON (" + ex + ")");
		}
    }//GEN-LAST:event_toJSONButtonActionPerformed

    private void ModuleTextFieldNAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModuleTextFieldNAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModuleTextFieldNAMEActionPerformed

    private void ModuleTextFieldEXTENDEDNAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModuleTextFieldEXTENDEDNAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModuleTextFieldEXTENDEDNAMEActionPerformed

    private void CreateCalendarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateCalendarButtonActionPerformed
        CalendarMenu calendar = new CalendarMenu(this.module);
		calendar.setVisible(true);
    }//GEN-LAST:event_CreateCalendarButtonActionPerformed

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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ModuleMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}*/
		//</editor-fold>
		//</editor-fold>

		//</editor-fold>
		//</editor-fold>

		/* Create and display the form *//*
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ModuleMenu().setVisible(true);
			}
		});
	}*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssignTypes;
    private javax.swing.JTable AssignTypesTable;
    private javax.swing.JLabel AssignTypesTitle;
    private javax.swing.JButton CreateCalendarButton;
    private javax.swing.JCheckBox DayTemplateCheckBox;
    private javax.swing.JPanel Hospital;
    private javax.swing.JLabel HospitalLabelID;
    private javax.swing.JLabel HospitalLabelLOCATION;
    private javax.swing.JLabel HospitalLabelNAME;
    private javax.swing.JTextField HospitalTextFieldID;
    private javax.swing.JTextField HospitalTextFieldLOCATION;
    private javax.swing.JTextField HospitalTextFieldNAME;
    private javax.swing.JLabel HospitalTitle;
    private javax.swing.JPanel Jobs;
    private javax.swing.JTable JobsTable;
    private javax.swing.JLabel JobsTitle;
    private javax.swing.JButton LoadButton;
    private javax.swing.JPanel Members;
    private javax.swing.JTable MembersTable;
    private javax.swing.JLabel MembersTitle;
    private javax.swing.JPanel ModuleINFO;
    private javax.swing.JLabel ModuleLabelDAYGROUPS;
    private javax.swing.JLabel ModuleLabelDESCRIPTION;
    private javax.swing.JLabel ModuleLabelEXTENDEDNAME;
    private javax.swing.JLabel ModuleLabelID;
    private javax.swing.JLabel ModuleLabelNAME;
    private javax.swing.JLabel ModuleLabelYEAR;
    private javax.swing.JTextField ModuleTextFieldDAYGROUPS;
    private javax.swing.JTextField ModuleTextFieldDESCRIPTION;
    private javax.swing.JTextField ModuleTextFieldEXTENDEDNAME;
    private javax.swing.JTextField ModuleTextFieldID;
    private javax.swing.JTextField ModuleTextFieldNAME;
    private javax.swing.JTextField ModuleTextFieldYEAR;
    private javax.swing.JLabel ModuleTitle;
    private javax.swing.JPanel PetitionTypes;
    private javax.swing.JTable PetitionTypesTable;
    private javax.swing.JLabel PetitionTypesTitle;
    private javax.swing.JPanel ResTypes;
    private javax.swing.JTable ResTypesTable;
    private javax.swing.JLabel ResTypesTitle;
    private javax.swing.JPanel Rules;
    private javax.swing.JTable RulesTable;
    private javax.swing.JLabel RulesTitle;
    private javax.swing.JButton SaveButton;
    private javax.swing.JPanel Templates;
    private javax.swing.JTable TemplatesTable;
    private javax.swing.JLabel TemplatesTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton toJSONButton;
    // End of variables declaration//GEN-END:variables
}
