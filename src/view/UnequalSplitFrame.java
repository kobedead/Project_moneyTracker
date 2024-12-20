package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UnequalSplitFrame extends JFrame implements PropertyChangeListener, ListSelectionListener {

    public UnequalSplitFrame(){super("Payments");}
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
