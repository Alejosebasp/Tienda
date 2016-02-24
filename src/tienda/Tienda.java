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
        
        int eleccion1=0, eleccion2=0, eleccion3=0, num_prod=0;
        int valorVenta, valorTotal=0, totalProductos, valorProducto=0, descuento;
        int num_venta=0, num_vend;
        int a =0, b=0;
        
        String[][] totalProd = new String[3][200];
        String[][] vendedores = new String[4][50];
        for(int k=0; k<50; k++){
            vendedores[3][k]="0";
        }
        String[][] ventas = new String[5][300];    
        String[][] facturas = new String[12][300];
        
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
                            +"\n4. Para determinar las ventas de x vendedor."
                            +"\n5. Para conocer la mayor venta con tarjeta."
                            +"\n6. Para ver el listado de las ventas por débito."
                            +"\n0. Para cambiar de rol.");
                eleccion2 = leer.nextInt();
            
                switch (eleccion2){
                    case 1:{                        
                        System.out.println("Digite el numero de productos que desea agregar");
                        num_prod = leer.nextInt();
                        
                        totalProd = agregarProducto(totalProd, num_prod, a);
                        a=a+num_prod;
                        imprimirProductos(totalProd, a);
                    break;}
                    
                    case 2:
                        System.out.println("Digite el numero de vendedores que desea agregar");
                        num_vend = leer.nextInt();
                        
                        vendedores = agregarVendedor(vendedores, num_vend, b);
                        b=b+num_vend;
                    break;
                    
                    case 3:
                        System.out.println("El valor total de las ventas en el dia es: " + valorTotal
                                +" y el numero de ventas realizadas son: "+num_venta);
                        break;
                        
                    case 4:
                        evaluarVenta(facturas, num_venta);
                        break;
                        
                    case 5:
                        ventasTarjeta(facturas, num_venta);
                        break;
                    case 6:
                        ventasDebito(facturas, num_venta);
                        break;
                        
                    case 7:
                        vendedorMayor(vendedores, b);
                        break;
                    case 0:
                        break;
                    default :
                        System.out.println("El valor ingresado no es correcto.");
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
                        
                        for (int k=0; k<a; k++){
                            if (ventas[2][num_venta].equals(totalProd[0][k]) ){
                                valorProducto = Integer.parseInt(totalProd[1][k]);
                                break;
                            }
                        }
                                                
                        valorVenta = ((valorProducto - ((valorProducto*descuento)/(100)))*totalProductos);
                        valorTotal = valorTotal+valorVenta;
                        facturas = crearFactura(ventas, num_venta, valorProducto, valorVenta, descuento, vendedores, facturas);
                        imprimirFactura(facturas, num_venta);
                        
                        num_venta=num_venta+1;
                    break;
                    
                    case 0:
                        break;
                    default :
                        System.out.println("El valor ingresado no es correcto.");
                        break;
                }
            break;}
            } 
        } while(eleccion1!=0);
    }
    
    public static void imprimirProductos(String[][] matriz, int columna ){
        
        System.out.print("Codigo:     ");
        for (int i=0; i<columna; i++){            
            System.out.print("  "+matriz[0][i]+"  ");
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
    
    public static String[][] agregarProducto (String[][] productos, int num_prod, int a){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        
        for (int i=a; i<(num_prod+a); i++){
            
            System.out.println("Digite el codigo del producto "+(i+1)+".");                
            productos[0][i]= leer.nextLine();

            System.out.println("Digite el valor del producto "+(i+1)+".");
            productos[1][i]= leer.nextLine();

            System.out.println("Digite la descripcion del producto "+(i+1)+".");
            productos[2][i]= leer.nextLine();
        }
        
        return productos;
    }
    
    
    public static String[][] agregarVendedor (String[][] vendedores, int num_vend, int b){
        java.util.Scanner leer = new java.util.Scanner(System.in);        
        
        for (int i=b; i<(b+num_vend); i++){
            
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

        System.out.println("Digite la fecha y hora de la venta (d/m/a hora) "+(num_venta+1)+".");                
        ventas[0][num_venta]= leer.nextLine();

        System.out.println("Digite el id del vendedor.");
        ventas[1][num_venta]=leer.nextLine();
        
        System.out.println("Digite el codigo del producto.");
        ventas[2][num_venta]= leer.nextLine();
        
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
    
    public static String[][] crearFactura(String[][] ventas, int num_venta, 
            int valorProducto, int valorVenta, int descuento, String[][] vendedores, String[][] facturas){
        
        facturas[0][num_venta] = String.valueOf(num_venta+1);
        facturas[1][num_venta] = ventas[0][num_venta];            
        
        for (int h=0; h<50; h++){
            if (ventas[1][num_venta].equals(vendedores[0][h])){
                facturas[2][num_venta] = vendedores[0][h];
                facturas[3][num_venta] = vendedores[1][h];
                facturas[4][num_venta] = vendedores[2][h];
                vendedores[3][h] = String.valueOf(1+(Integer.parseInt(vendedores[3][h])));
            }
        }
        
        facturas[5][num_venta] = ventas[2][num_venta];     
        facturas[6][num_venta] = String.valueOf(valorProducto);    
        facturas[7][num_venta] = ventas[3][num_venta];    
        facturas[8][num_venta] = ventas[4][num_venta];    
        facturas[9][num_venta] = String.valueOf(descuento);    
        facturas[10][num_venta] = String.valueOf(valorVenta);    
        facturas[11][num_venta] = " ";
        
        return facturas;        
    }
    
    public static void imprimirFactura(String[][] facturas, int num_venta){
        
        System.out.println(facturas[11][num_venta]);
        System.out.println("Factura de venta N° "+facturas[0][num_venta]);
        System.out.println("Fecha: " + facturas[1][num_venta]);
        System.out.println(facturas[11][num_venta]);
        System.out.println("Id del vendedor: " + facturas[2][num_venta]);
        System.out.println("Nombre del vendedor: " + facturas[3][num_venta]);
        System.out.println("Apellido del vendedor: " + facturas[4][num_venta]);
        System.out.println(facturas[11][num_venta]);
        System.out.println("Codigo del producto--------- " + facturas[5][num_venta]);
        System.out.println("Valor unitario del producto- " + facturas[6][num_venta]);
        System.out.println("Cantidad del producto------- " + facturas[7][num_venta]);
        System.out.println("Foma de pago---------------- " + facturas[8][num_venta]);
        System.out.println("Descuento------------------- " + facturas[9][num_venta] +"%");
        System.out.println("Valor total de la venta----- " + facturas[10][num_venta]);
        System.out.println(facturas[11][num_venta]);
    }
    
    public static void evaluarVenta(String[][] facturas, int num_venta){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        String id, codigoProducto;
        
        System.out.println("Digite el id del vendedor que desea buscar.");
        id = leer.next();
        
        System.out.println("Digite el codigo del producto que desea consultar.");
        codigoProducto = leer.next();
        
        for (int i=0; i<num_venta; i++){
            if (facturas[2][i].equals(id)){
                if (facturas[5][i].equals(codigoProducto)){
                    imprimirFactura(facturas, i);
                }
            }
            else{
                System.out.println("El vendedor consultado no realizo ninguna venta.");
            }
        }
    }
    
    public static void ventasTarjeta(String[][] facturas, int num_venta){
        int valorMayor=0;
        
        for (int i=0; i<num_venta; i++){
            if (facturas[8][i].equals("Tarjeta")){
                if (Integer.parseInt(facturas[10][i])>valorMayor){
                    valorMayor=Integer.parseInt(facturas[10][i]);
                }
            }
        }
        
        for (int j=0; j<num_venta; j++){
            if(Integer.parseInt(facturas[10][j])==(valorMayor)){
            
                imprimirFactura(facturas, j);
            }
        }
    }

    public static void vendedorMayor(String[][] vendedores, int num_vendedores){
        int ven_may=0;
        
        for (int i=0; i<num_vendedores; i++){
            if (Integer.parseInt(vendedores[3][i])>ven_may){
                ven_may = Integer.parseInt(vendedores[3][i]);
            }            
        }
        
        for (int j=0; j<num_vendedores; j++){
            if (Integer.parseInt(vendedores[3][j])==ven_may){
                System.out.println("El vendedor con mayor numero de ventas es:");
                System.out.println("ID:       "+vendedores[0][j]);
                System.out.println("Nombre:   "+vendedores[1][j]);
                System.out.println("Apellido: "+vendedores[2][j]);
                System.out.println("Numero de ventas registradas: "+vendedores[3][j]);
            }
        }
        
    }

    public static void ventasDebito(String[][] facturas, int num_venta){
        
        for (int i=0; i<num_venta; i++){
            if (facturas[8][i].equals("Debito")){
                System.out.println("Id vendedor: "+facturas[2][i]+", venta debito, "+"valor total de la venta: "
                        +facturas[10][i]);
            }
        }
    }
}