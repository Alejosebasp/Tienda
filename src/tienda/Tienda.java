/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

/**
 * @version 1.0
 * @author alejandrosebastian
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.util.Scanner leer = new java.util.Scanner(System.in);
        
        int eleccion1=0, eleccion2=0, eleccion3=0;
        int valorVenta, totalProductos, valorProducto, descuento;
                
        String[][] totalProd = new String[3][];
        String[][] vendedores = new String[3][];
        String[][] ventas = new String[5][200];
        
        int num_venta=0;
        
        do{
        System.out.println("Seleccione"
                            +"\n1. Para rol administrativo."
                            +"\n2. Para rol de vendedor."
                            +"\n0. Para cerrar el programa.");
        eleccion1 = leer.nextInt();
        switch (eleccion1){
            case 1:{
                System.out.println("Seleccione"
                            +"\n1. Para ver y añadir un productos."
                            +"\n2. Para añadir un vendedor."
                            +"\n3. Para saber las ventas del día."
                            +"\n0. Para cambiar de rol.");
                eleccion2 = leer.nextInt();
            
                switch (eleccion2){
                    case 1:{                        
                        System.out.println("Digite el numero de productos que desea agregar");
                        int num_prod = leer.nextInt();
                        
                        String[][] productos = new String[3][num_prod];                        
                        productos = agregarProducto(productos, num_prod);
                        totalProd = productos;
                        imprimirProductos(totalProd, num_prod);
                    break;}
                    
                    case 2:
                        System.out.println("Digite el numero de vendedores que desea agregar");
                        int num_vend = leer.nextInt();
                        
                        String[][] vendedor = new String[3][num_vend];                        
                        vendedor = agregarVendedor(vendedor, num_vend);
                        vendedores = vendedor;
                                                
                    break;    
                    case 0:
                        break;
            }
        break;}
            
            case 2:{
                System.out.println("Selesccione"
                            +"\n1. Para registrar una venta."
                            +"\n0. Para cambiar de rol");
                eleccion3 = leer.nextInt();
                
                switch (eleccion3){
                    case 1:
                        ventas = registrarVenta(ventas, vendedores, totalProd, num_venta);
                        
                        System.out.println("Ingrese el valor del descuento(0-100)");
                        descuento = leer.nextInt();
                        
                        totalProductos = Integer.parseInt(ventas[3][num_venta]);
                        valorProducto = Integer.parseInt(ventas[1][num_venta]);
                        valorVenta = ((valorProducto - ((valorProducto*descuento)/(100)))*totalProductos);
                        
                        imprimirFactura(ventas, num_venta, valorProducto, valorVenta, descuento);
                        
                        num_venta=num_venta+1;
                    break;
                    
                    case 0:
                        break;
                }
            break;}
        }       
            
        }
        while(eleccion1!=0);
    }
    
    public static void imprimirProductos(String[][] matriz, int columna ){
        
        System.out.print("Codigo:     ");
        for (int i=0; i<columna; i++){            
            System.out.print(" "+matriz[0][i]+" ");
        }
        System.out.println("");
        System.out.print("Precio:     ");
        for (int j=0; j<columna; j++){
            System.out.print(" "+matriz[1][j]+ " ");
        }
        System.out.println("");
        System.out.print("Descripcion:");
        for (int h=0; h<columna; h++){
            System.out.print(" "+matriz[2][h]+" ");
        }
        System.out.println("");
        System.out.println("");
    }
    
    public static String[][] agregarProducto (String[][] productos, int num_prod){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        
        
        for (int i=0; i<num_prod; i++){
            
                System.out.println("Digite el codigo del producto "+(i+1)+".");                
                productos[0][i]= leer.nextLine();
                
                System.out.println("Digite el valor del producto "+(i+1)+".");
                productos[1][i]= leer.nextLine();
                
                System.out.println("Digite la descripcion del producto "+(i+1)+".");
                productos[2][i]= leer.nextLine();
        }
        
        return productos;
    }
    
    
    public static String[][] agregarVendedor (String[][] vendedores, int num_vend){
        java.util.Scanner leer = new java.util.Scanner(System.in);        
        
        for (int i=0; i<num_vend; i++){
            
                System.out.println("Digite el id del vendedor "+(i+1)+".");                
                vendedores[0][i]= leer.nextLine();
                
                System.out.println("Digite el nombre del vendedor "+(i+1)+".");
                vendedores[1][i]= leer.nextLine();
                
                System.out.println("Digite el apellido del vendedor "+(i+1)+".");
                vendedores[2][i]= leer.nextLine();
        }        
        return vendedores;
    }
    
    public static String [][] registrarVenta(String[][] ventas, String[][] vendedores, String[][] totalProd, int num_venta){
        java.util.Scanner leer = new java.util.Scanner(System.in);

        System.out.println("Digite la fecha y hora de la venta(d/m/a hora) "+(num_venta+1)+".");                
        ventas[0][num_venta]= leer.nextLine();

        System.out.println("Digite el id del vendedor");
        String id = leer.nextLine();
        
        int e=1;
        while(e==1){
        for (int i=0; i<vendedores.length; i++){
            for (int j=0; i<vendedores[i].length; j++){
                if (vendedores[i][j].equals(id)){
                    ventas[1][num_venta]= id;
                    e=0;
                }                
            }            
        }
        
        if (e==1){
                System.out.print("El id del vendedor registrado no existe.");
            }        
        }        
        
        System.out.println("Digite el codigo del producto.");
        
        while(e==0){
            for (int c=0; c<totalProd.length; c++){
                for (int d=0; d<totalProd[c].length; d++){
                    if (totalProd[c][d].equals(leer.next())){
                        ventas[2][num_venta]= leer.nextLine();
                        e=1;
                    }                    
                }
            }
            if (e==0){
                System.out.println("El produto ingresado no existe.");
            }            
        }

        System.out.println("Digite la cantidad del producto.");
        ventas[3][num_venta]= leer.nextLine();
        
        System.out.println("Digite la forma de pago (0-efectivo, 1-debito, 2-tarjeta).");
        int pago = leer.nextInt();
        
        switch (pago){
            case 0:
               ventas[4][num_venta]= "Efectivo";
               break;
            case 1:
                ventas[4][num_venta]= "Debito";
                break;
            case 2:
                ventas[4][num_venta]= "Tarjeta";
                break;            
        }                
        return ventas;
    }
    
    public static void imprimirFactura(String[][] ventas, int num_venta, int valorProducto, int valorVenta, int descuento){
        
        System.out.println("Fecha: " + ventas[0][num_venta]);
        System.out.println("");
        System.out.println("Vendedor: " + ventas[1][num_venta]);
        System.out.println("");
        System.out.println("Codigo del producto: " + ventas[2][num_venta]);  
        System.out.println("Valor unitario del producto: "+ valorProducto);
        System.out.println("Cantidad del producto: " + ventas[3][num_venta]);
        System.out.println("Foma de pago: " + ventas[4][num_venta]);
        System.out.println("Descuento: "+descuento+"%");
        System.out.println("Valor total de la venta: "+valorVenta);
        
    }
}
