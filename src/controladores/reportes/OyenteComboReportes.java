package controladores.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.db.SQLReportes;
import vistas.reportes.PanelReportes;

public class OyenteComboReportes implements ActionListener {

    private SQLReportes reportes;
    private PanelReportes panel;
    private boolean banderaCompras = false;
    private boolean banderaVentas = false;

    public OyenteComboReportes(SQLReportes reportes, PanelReportes panel) {
        this.reportes = reportes;
        this.panel = panel;
        reportes.cargaComboFiltros(panel.getComboFiltros());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tablaReportes = panel.getTablaReportes();
        int indice = panel.getComboFiltros().getSelectedIndex();
        this.gestionaFehcas(tablaReportes, indice);
    }

    public void gestionaFehcas(JTable tablaReportes, int indice) {
        try {
            if (indice > 4) {
                if ((indice == 5) && !banderaCompras) {
                    banderaCompras = true;
                    banderaVentas = false;
                    panel.getComboFechas().setEnabled(true);
                    reportes.cargarMesesCompras(panel.getComboFechas());
                }
                if ((indice == 6) && !banderaVentas) {
                    banderaCompras = false;
                    banderaVentas = true;
                    panel.getComboFechas().setEnabled(true);
                    reportes.cargarMesesVentas(panel.getComboFechas());
                }
                String fecha = panel.getComboFechas().getSelectedItem().toString();
                reportes.estableceFiltros(tablaReportes, indice, fecha);
            } else {
                panel.getComboFechas().setEnabled(false);
                reportes.estableceFiltros(tablaReportes, indice, "");
            }
        } catch (NullPointerException e) {
//            panel.getComboFechas().removeAllItems();
//            reportes.cargarMesesVentas(panel.getComboFechas());
        }
    }

}
