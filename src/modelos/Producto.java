/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author jarv
 */
public class Producto {
    
    private long idProducto;
    private String colorProducto;
    private String tamanioProducto;
    private String marcaProducto;
    private String descripcionProducto;
    private double marcoGanancia;
    private int existenciaProducto;
    private int idCategoria;

    /**
     * @return the idProducto
     */
    public long getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the colorProducto
     */
    public String getColorProducto() {
        return colorProducto;
    }

    /**
     * @param colorProducto the colorProducto to set
     */
    public void setColorProducto(String colorProducto) {
        this.colorProducto = colorProducto;
    }

    /**
     * @return the tamanioProducto
     */
    public String getTamanioProducto() {
        return tamanioProducto;
    }

    /**
     * @param tamanioProducto the tamanioProducto to set
     */
    public void setTamanioProducto(String tamanioProducto) {
        this.tamanioProducto = tamanioProducto;
    }

    /**
     * @return the marcaProducto
     */
    public String getMarcaProducto() {
        return marcaProducto;
    }

    /**
     * @param marcaProducto the marcaProducto to set
     */
    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    /**
     * @return the descripcionProducto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * @param descripcionProducto the descripcionProducto to set
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * @return the marcoGanancia
     */
    public double getMarcoGanancia() {
        return marcoGanancia;
    }

    /**
     * @param marcoGanancia the marcoGanancia to set
     */
    public void setMarcoGanancia(double marcoGanancia) {
        this.marcoGanancia = marcoGanancia;
    }

    /**
     * @return the existenciaProducto
     */
    public int getExistenciaProducto() {
        return existenciaProducto;
    }

    /**
     * @param existenciaProducto the existenciaProducto to set
     */
    public void setExistenciaProducto(int existenciaProducto) {
        this.existenciaProducto = existenciaProducto;
    }

    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
