package gui.merger;

import javax.swing.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.*;

import gui.sections.IMergable;
import sections.Section;

public class SectionHolderGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private SectionHolderGUI symmetrical;
    private JPanel section;
    private JButton btnArrow;

    private String firstColumnAttributes;
    private String secondColumnAttributes;
    private String buttonPositionAttributes;
    private String sectionPositionAttributes;

    public SectionHolderGUI(final int sectionIndex, final JPanel section,
            final Section model, final CVMergerController controller,
            boolean isLeft) {
        decideAttributes(isLeft);
        this.section = section;
        section.setEnabled(false);
        IMergable mergableSection = (IMergable)section;
        mergableSection.setInputEnabled(false);
        setLayout(new FormLayout(new ColumnSpec[] {
            ColumnSpec.decode(firstColumnAttributes),
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode(secondColumnAttributes),},
                new RowSpec[] {
                    FormSpecs.DEFAULT_ROWSPEC,}));

        add(section, sectionPositionAttributes);

        btnArrow = new JButton("");
        btnArrow.setToolTipText("Use this version in the merged CV.");
        btnArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controller.sectionSelected(sectionIndex, model);
                section.setEnabled(true);
                setSectionInputEnabled(true);
                btnArrow.setEnabled(false);
                symmetrical.setButtonEnabled(true);
                symmetrical.setSectionEnabled(false);
                symmetrical.setSectionInputEnabled(false);
            }
        });
        btnArrow.setIcon(new ImageIcon("resources/button_check.png"));
        add(btnArrow, buttonPositionAttributes);
        setUpTimer();
    }

    private void setUpTimer() {
        ActionListener symmetricalChecker = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IMergable mergable = (IMergable)section;
                IMergable otherMergable = (IMergable)symmetrical.section;
                mergable.compareToSymmetrical(otherMergable);
            }
        };
        Timer timer = new Timer(1000 ,symmetricalChecker);
        timer.setRepeats(true);
        timer.start();
    }

    public void setSymmetrical(SectionHolderGUI symmetrical) {
        this.symmetrical = symmetrical;
    }
    public void setButtonEnabled(boolean enabled) {
        btnArrow.setEnabled(enabled);
    }

    public void setSectionEnabled(boolean enabled) {
        section.setEnabled(enabled);
    }

    public void setSectionInputEnabled(boolean enabled) {
        ((IMergable)section).setInputEnabled(enabled);
    }

    private void decideAttributes(boolean isLeft) {
        if (isLeft) {
            firstColumnAttributes = "default:grow";
            secondColumnAttributes = "center:default";
            buttonPositionAttributes ="3, 1";
            sectionPositionAttributes = "1, 1, fill, default";
        } else {
            firstColumnAttributes = "center:default";
            secondColumnAttributes = "default:grow";
            buttonPositionAttributes = "1, 1";
            sectionPositionAttributes = "3, 1, fill, default";
        }
    }

}
