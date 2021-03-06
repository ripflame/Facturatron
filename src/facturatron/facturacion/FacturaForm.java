/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacturaForm.java
 *
 * Created on 27/07/2010, 12:23:19 PM
 */

package facturatron.facturacion;

import com.alee.laf.button.WebButton;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Octavio
 */
public class FacturaForm extends javax.swing.JPanel implements Observer {

    private FacturaDao modelo;

    public JFrame getJFrameParent() {
        return (JFrame) JFrame.getFrames()[0];
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.getTxtSerie().setText(getModelo().getSerie());
        this.getTxtFolio().setText(String.format("%d", getModelo().getFolio()));
        this.getTxtFormaDePago().setText(getModelo().getFormaDePago());
        this.getTxtMetodoPago().setText(getModelo().getMetodoDePago());
        
        this.getTxtNombre().setText(String.valueOf(getModelo().getReceptor().getNombre()));
        this.getTxtRfc().setText(String.valueOf(getModelo().getReceptor().getRfc()));
        this.getTxtDireccion().setText(getModelo().getReceptor().getCalle() +
                " # " + getModelo().getReceptor().getNoExterior() +
                "\r\n" + getModelo().getReceptor().getColonia() +
                "\r\n" + getModelo().getReceptor().getMunicipio() +
                ", " + getModelo().getReceptor().getEstado() +
                "\r\nCP " + getModelo().getReceptor().getCodigoPostal());

        String subtotal = String.format("%,.2f", getModelo().getSubtotal());
        this.getTxtSubtotal().setText(subtotal);
        String iva  = String.format("%,.2f", getModelo().getIvaTrasladado());
        this.getTxtIva().setText(iva);
        String ieps = String.format("%,.2f", getModelo().getIEPSTrasladado());
        this.getTxtIEPS().setText(ieps)
                ;
        String total = String.format("%,.2f", getModelo().getTotal());
        this.getTxtTotal().setText(total);
    }
    
   

    /** Creates new form FacturaForm */
    public FacturaForm() {
        initComponents();
        tabConceptos.addKeyListener(new java.awt.event.KeyAdapter() {  
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {  
                whichKeyPressed(evt);  
            }  
        }); 
        txtDireccion.setEditable(false);
        tabConceptos.setSortable(false);    
        
        tabConceptos.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, false), "selectNextColumnCell");
        ActionMap am = tabConceptos.getActionMap();
        am.put("selectPreviousColumnCell", new PreviousFocusHandler());    
        am.put("selectNextColumnCell", new NextFocusHandler());  
    }
    private class PreviousFocusHandler extends AbstractAction {
        public void actionPerformed(ActionEvent evt) {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusPreviousComponent();
        }
    }

    private class NextFocusHandler extends AbstractAction {
        public void actionPerformed(ActionEvent evt) {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusNextComponent();
        }
    }

    //Metodo para intercambiar con la tecla <tab>  enter entre columnas de la tabla tabConceptos
    private void whichKeyPressed(java.awt.event.KeyEvent evt) {  
        int column = tabConceptos.getSelectedColumn();  
        int row = tabConceptos.getSelectedRow();  
        int rows = tabConceptos.getRowCount();             
        if(evt.getKeyCode() == KeyEvent.VK_ENTER ||  
            evt.getKeyCode() == KeyEvent.VK_TAB && !evt.isShiftDown() ){
            if( column == 0){                  
                tabConceptos.changeSelection(row, 1, false,  false);  
                tabConceptos.editCellAt(row,1);                
            }  
            else if( column == 1){  
                tabConceptos.changeSelection(row, 2, false,  false);  
                tabConceptos.editCellAt(row,2);                
            }  
            else if( column == 2){  
                tabConceptos.changeSelection(row, 3, false,  false);  
                tabConceptos.editCellAt(row,3);  
            }
            else if( column == 3){  
                tabConceptos.changeSelection(row, 4, false,  false);  
                tabConceptos.editCellAt(row,4);  
            }
            else if (column == 4 ) {
                tabConceptos.changeSelection(row, 5, false,  false);  
                tabConceptos.editCellAt(row,5);
            }
            else if (column == 5 ) {
                tabConceptos.changeSelection(row, 6, false,  false);  
                tabConceptos.editCellAt(row,6);
            }
            else if( column == 6){  
                if( row == (rows - 1) )  
                    row = -1;  
                tabConceptos.changeSelection(row + 1, 0, false,  false);  
                tabConceptos.editCellAt(row + 1,0);  
            }  
            evt.consume();  
        } else if((evt.getKeyCode() == KeyEvent.VK_TAB) && evt.isShiftDown()) {
            Integer col = column;
            switch(column) {
                case 0:
                    if(row-1 >= 0) { row--; col = 6; } break;
                default:
                    col--; break;                
            }
            tabConceptos.changeSelection(row, col, false,  false);  
            tabConceptos.editCellAt(row, col);
            evt.consume();
        }
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jXTitledPanel2 = new org.jdesktop.swingx.JXTitledPanel();
        jLabel9 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFormaDePago = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        comboTipoComprobante = new javax.swing.JComboBox<TipoComprobante>();
        jLabel14 = new javax.swing.JLabel();
        txtMetodoPago = new javax.swing.JTextField();
        btnBuscarCliente = new com.alee.laf.button.WebButton();
        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabConceptos = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtDescuentoTasa16 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDescuentoTasa0 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMotivoDescuento = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtIEPS = new javax.swing.JTextField();
        jXTitledPanel3 = new org.jdesktop.swingx.JXTitledPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBorrarPartida = new javax.swing.JButton();
        btnObservaciones = new org.jdesktop.swingx.JXButton();
        btnFacturaDia = new javax.swing.JButton();
        btnTicket = new javax.swing.JButton();
        btnVistaPrevia = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(640, 480));

        jXTitledPanel2.setTitle("Datos Fiscales");
        jXTitledPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jXTitledPanel2.getContentContainer().setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Id Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jXTitledPanel2.getContentContainer().add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtIdCliente, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("RFC:");
        jLabel3.setPreferredSize(new java.awt.Dimension(64, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jXTitledPanel2.getContentContainer().add(jLabel3, gridBagConstraints);

        txtRfc.setEditable(false);
        txtRfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRfcActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtRfc, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dirección:");
        jLabel2.setMaximumSize(new java.awt.Dimension(64, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(64, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(64, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jXTitledPanel2.getContentContainer().add(jLabel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Razón Social:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jXTitledPanel2.getContentContainer().add(jLabel1, gridBagConstraints);

        txtNombre.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtNombre, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Folio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jXTitledPanel2.getContentContainer().add(jLabel10, gridBagConstraints);

        txtFolio.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtFolio, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Serie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jXTitledPanel2.getContentContainer().add(jLabel4, gridBagConstraints);

        txtSerie.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtSerie, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jXTitledPanel2.getContentContainer().add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtFormaDePago, gridBagConstraints);

        txtDireccion.setColumns(20);
        txtDireccion.setRows(4);
        txtDireccion.setPreferredSize(new java.awt.Dimension(40, 80));
        jScrollPane2.setViewportView(txtDireccion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(jScrollPane2, gridBagConstraints);

        comboTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(TipoComprobante.values()));
        comboTipoComprobante.setSelectedItem(TipoComprobante.FACTURA);
        comboTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoComprobanteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(comboTipoComprobante, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Método de pago:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jXTitledPanel2.getContentContainer().add(jLabel14, gridBagConstraints);

        txtMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMetodoPagoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(txtMetodoPago, gridBagConstraints);

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 0, 0);
        jXTitledPanel2.getContentContainer().add(btnBuscarCliente, gridBagConstraints);

        jXTitledPanel1.setTitle("Conceptos");
        jXTitledPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tabConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabConceptos);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Descuento Tasa 16:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel12, gridBagConstraints);

        txtDescuentoTasa16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtDescuentoTasa16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDescuentoTasa16.setMinimumSize(new java.awt.Dimension(14, 24));
        txtDescuentoTasa16.setPreferredSize(new java.awt.Dimension(14, 24));
        txtDescuentoTasa16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoTasa16ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtDescuentoTasa16, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel13, gridBagConstraints);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTotal.setMinimumSize(new java.awt.Dimension(14, 24));
        txtTotal.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtTotal, gridBagConstraints);

        txtIva.setEditable(false);
        txtIva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIva.setMinimumSize(new java.awt.Dimension(14, 24));
        txtIva.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtIva, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("IVA (16%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSubtotal.setMinimumSize(new java.awt.Dimension(14, 24));
        txtSubtotal.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtSubtotal, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Subtotal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        txtDescuentoTasa0.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtDescuentoTasa0.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDescuentoTasa0.setMinimumSize(new java.awt.Dimension(14, 24));
        txtDescuentoTasa0.setPreferredSize(new java.awt.Dimension(14, 24));
        txtDescuentoTasa0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoTasa0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtDescuentoTasa0, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Descuento Tasa 0:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Motivo Descuento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        txtMotivoDescuento.setMinimumSize(new java.awt.Dimension(14, 24));
        txtMotivoDescuento.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtMotivoDescuento, gridBagConstraints);

        jLabel15.setText("IEPS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel15, gridBagConstraints);

        txtIEPS.setEditable(false);
        txtIEPS.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIEPS.setMinimumSize(new java.awt.Dimension(14, 24));
        txtIEPS.setPreferredSize(new java.awt.Dimension(14, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(txtIEPS, gridBagConstraints);

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jXTitledPanel3.setTitle("Acciones");
        jXTitledPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnBorrarPartida.setText("Borrar partida");
        btnBorrarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPartidaAction(evt);
            }
        });
        jPanel1.add(btnBorrarPartida, new java.awt.GridBagConstraints());

        btnObservaciones.setText("Ver/Editar Observaciones");
        btnObservaciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservacionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnObservaciones, new java.awt.GridBagConstraints());

        btnFacturaDia.setText("Factura del día");
        jPanel1.add(btnFacturaDia, new java.awt.GridBagConstraints());

        btnTicket.setText("Añadir ticket");
        jPanel1.add(btnTicket, new java.awt.GridBagConstraints());

        btnVistaPrevia.setText("Vista previa");
        jPanel1.add(btnVistaPrevia, new java.awt.GridBagConstraints());

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar factura");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(btnGuardar, gridBagConstraints);

        javax.swing.GroupLayout jXTitledPanel3Layout = new javax.swing.GroupLayout(jXTitledPanel3.getContentContainer());
        jXTitledPanel3.getContentContainer().setLayout(jXTitledPanel3Layout);
        jXTitledPanel3Layout.setHorizontalGroup(
            jXTitledPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXTitledPanel3Layout.setVerticalGroup(
            jXTitledPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTitledPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTitledPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTitledPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTitledPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtRfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRfcActionPerformed

    private void comboTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoComprobanteActionPerformed

    private void txtMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMetodoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMetodoPagoActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnObservacionesActionPerformed

    private void txtDescuentoTasa16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoTasa16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoTasa16ActionPerformed

    private void txtDescuentoTasa0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoTasa0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoTasa0ActionPerformed

    private void borrarPartidaAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPartidaAction
        if(tabConceptos.getSelectedRowCount() > 0 && tabConceptos.getRowCount() > 1) {
            Integer selected = tabConceptos.getSelectionModel().getMinSelectionIndex();
            ((FacturaTableModel)tabConceptos.getModel()).removeRow(selected);
        }
    }//GEN-LAST:event_borrarPartidaAction


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarPartida;
    private com.alee.laf.button.WebButton btnBuscarCliente;
    private javax.swing.JButton btnFacturaDia;
    private javax.swing.JButton btnGuardar;
    private org.jdesktop.swingx.JXButton btnObservaciones;
    private javax.swing.JButton btnTicket;
    private javax.swing.JButton btnVistaPrevia;
    private javax.swing.JComboBox<TipoComprobante> comboTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel2;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel3;
    private org.jdesktop.swingx.JXTable tabConceptos;
    private javax.swing.JFormattedTextField txtDescuentoTasa0;
    private javax.swing.JFormattedTextField txtDescuentoTasa16;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtFormaDePago;
    private javax.swing.JTextField txtIEPS;
    private javax.swing.JFormattedTextField txtIdCliente;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtMetodoPago;
    private javax.swing.JFormattedTextField txtMotivoDescuento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables




    /**
     * @return the guarda
     */
    public javax.swing.JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * @param guarda the guarda to set
     */
    public void setBtnGuardar(javax.swing.JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    /**
     * @return the tabConceptos
     */
    public javax.swing.JTable getTabConceptos() {
        return tabConceptos;
    }

    /**
     * @param tabConceptos the tabConceptos to set
     */
    public void setTabConceptos(javax.swing.JTable tabConceptos) {
        this.tabConceptos = (JXTable) tabConceptos;
        
    }

    /**
     * @return the txtDescuento
     */
    public javax.swing.JFormattedTextField getTxtDescuentoTasa0() {
        return txtDescuentoTasa0;
    }

    /**
     * @param txtDescuento the txtDescuento to set
     */
    public void setTxtDescuentoTasa0(javax.swing.JFormattedTextField txtDescuentoTasa0) {
        this.txtDescuentoTasa0 = txtDescuentoTasa0;
    }
    /**
     * @return the txtDescuento
     */
    public javax.swing.JFormattedTextField getTxtDescuentoTasa16() {
        return txtDescuentoTasa16;
    }

    /**
     * @param txtDescuento the txtDescuento to set
     */
    public void setTxtDescuentoTasa16(javax.swing.JFormattedTextField txtDescuentoTasa16) {
        this.txtDescuentoTasa16 = txtDescuentoTasa16;
    }

    /**
     * @return the txtDireccion
     */
    public javax.swing.JTextArea getTxtDireccion() {
        return txtDireccion;
    }

    /**
     * @param txtDireccion the txtDireccion to set
     */
    public void setTxtDireccion(javax.swing.JTextArea txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    /**
     * @return the txtFolio
     */
    public javax.swing.JTextField getTxtFolio() {
        return txtFolio;
    }

    /**
     * @param txtFolio the txtFolio to set
     */
    public void setTxtFolio(javax.swing.JTextField txtFolio) {
        this.txtFolio = txtFolio;
    }

    /**
     * @return the txtFormaDePago
     */
    public javax.swing.JTextField getTxtFormaDePago() {
        return txtFormaDePago;
    }

    /**
     * @param txtFormaDePago the txtFormaDePago to set
     */
    public void setTxtFormaDePago(javax.swing.JTextField txtFormaDePago) {
        this.txtFormaDePago = txtFormaDePago;
    }

    /**
     * @return the txtIdCliente
     */
    public javax.swing.JTextField getTxtIdCliente() {
        return txtIdCliente;
    }

    /**
     * @param txtIdCliente the txtIdCliente to set
     */
    public void setTxtIdCliente(javax.swing.JFormattedTextField txtIdCliente) {
        this.txtIdCliente = txtIdCliente;
    }

    /**
     * @return the txtIva
     */
    public javax.swing.JTextField getTxtIva() {
        return txtIva;
    }

    /**
     * @param txtIva the txtIva to set
     */
    public void setTxtIva(javax.swing.JTextField txtIva) {
        this.txtIva = txtIva;
    }

    /**
     * @return the txtMotivoDescuento
     */
    public javax.swing.JTextField getTxtMotivoDescuento() {
        return txtMotivoDescuento;
    }

    /**
     * @param txtMotivoDescuento the txtMotivoDescuento to set
     */
    public void setTxtMotivoDescuento(javax.swing.JFormattedTextField txtMotivoDescuento) {
        this.txtMotivoDescuento = txtMotivoDescuento;
    }

    /**
     * @return the txtNombre
     */
    public javax.swing.JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @param txtNombre the txtNombre to set
     */
    public void setTxtNombre(javax.swing.JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * @return the txtRfc
     */
    public javax.swing.JTextField getTxtRfc() {
        return txtRfc;
    }

    /**
     * @param txtRfc the txtRfc to set
     */
    public void setTxtRfc(javax.swing.JTextField txtRfc) {
        this.txtRfc = txtRfc;
    }

    /**
     * @return the txtSerie
     */
    public javax.swing.JTextField getTxtSerie() {
        return txtSerie;
    }

    /**
     * @param txtSerie the txtSerie to set
     */
    public void setTxtSerie(javax.swing.JTextField txtSerie) {
        this.txtSerie = txtSerie;
    }

    /**
     * @return the txtSubtotal
     */
    public javax.swing.JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    /**
     * @param txtSubtotal the txtSubtotal to set
     */
    public void setTxtSubtotal(javax.swing.JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    /**
     * @return the txtTotal
     */
    public javax.swing.JTextField getTxtTotal() {
        return txtTotal;
    }

    /**
     * @param txtTotal the txtTotal to set
     */
    public void setTxtTotal(javax.swing.JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * @return the modelo
     */
    public FacturaDao getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(FacturaDao modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the btnBuscarCliente
     */
    public WebButton getBtnBuscarCliente() {
        return btnBuscarCliente;
    }

    /**
     * @param btnBuscarCliente the btnBuscarCliente to set
     */
    public void setBtnBuscarCliente(WebButton btnBuscarCliente) {
        this.btnBuscarCliente = btnBuscarCliente;
    }

    /**
     * @return the btnObservaciones
     */
    public org.jdesktop.swingx.JXButton getBtnObservaciones() {
        return btnObservaciones;
    }

    /**
     * @return the btnTicket
     */
    public javax.swing.JButton getBtnTicket() {
        return btnTicket;
    }

    /**
     * @param btnTicket the btnTicket to set
     */
    public void setBtnTicket(javax.swing.JButton btnTicket) {
        this.btnTicket = btnTicket;
    }

    /**
     * @return the txtMetodoPago
     */
    public javax.swing.JTextField getTxtMetodoPago() {
        return txtMetodoPago;
    }

    /**
     * @param txtMetodoPago the txtMetodoPago to set
     */
    public void setTxtMetodoPago(javax.swing.JTextField txtMetodoPago) {
        this.txtMetodoPago = txtMetodoPago;
    }

    /**
     * @return the btnFacturaDia
     */
    public javax.swing.JButton getBtnFacturaDia() {
        return btnFacturaDia;
    }
    
    public JComboBox<TipoComprobante> getComboTipoComprobante() {
        return comboTipoComprobante;
    }

    public TipoComprobante getTipoComprobante() {
        return (TipoComprobante) comboTipoComprobante.getSelectedItem();
    }

    /**
     * @return the txtIEPS
     */
    public javax.swing.JTextField getTxtIEPS() {
        return txtIEPS;
    }
    
    public javax.swing.JButton getBtnVistaPrevia() {
        return btnVistaPrevia;
    }
}
