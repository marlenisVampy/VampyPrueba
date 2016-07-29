//VampySoft.java										//MJCC  100301097
import java.io.*;
import java.sql.*;
import java.util.*; 

//clase que me creara la base de datos y las tablas
class DataBaseV
{
	protected String nameDB = "VampyDB";
	protected Connection conn = null;
	protected Statement stat0 = null;
	protected ResultSet result = null;
	protected PreparedStatement stmt = null; 
	
	protected String URL = "jdbc:derby:" + nameDB + ";create=true"; 
	   
	  public void crearDB() throws Exception
	 { //metodo para crear la VampyDB
  			      		
		try{
		  //  Conexion a la base de datos via el JDBC Derby      
			conn = DriverManager.getConnection(URL);
			System.out.println("\n* Conexion exitosa con la base de datos " +  this.nameDB);	        
			conn.close();
		  }catch(Exception ei)
			{
				ei.getStackTrace();
			}
    
	}
  
  public void crearTablas() throws Exception 	//crear tablas VampyDB.
	{		
		try{
			
			conn = DriverManager.getConnection(URL);
			stat0 = conn.createStatement();
				
			String cad0 = "CREATE TABLE Cliente " + 
			 "( clienteID BIGINT NOT NULL PRIMARY KEY,"+
	         "  clienteNombre VARCHAR(25) NOT NULL,"+
		     "  clienteTel VARCHAR(13) NOT NULL )";
			
			String cad1 = "CREATE TABLE Vendedor " + 
			"( vendedorID BIGINT NOT NULL PRIMARY KEY,"+
			"   v_nombre VARCHAR(25) NOT NULL )";
			 
			 
			 String cad2 = "CREATE TABLE Venta " + 
			" (ventaId BIGINT NOT NULL PRIMARY KEY,"+
			" vID BIGINT CONSTRAINT VendedorID_FK REFERENCES Vendedor(vendedorID)," +
			" cliID BIGINT CONSTRAINT ClienteID_FK REFERENCES Cliente(clienteID))";
			
			String cad3 = "CREATE TABLE Calzado " + 
			" (calzadoID BIGINT NOT NULL PRIMARY KEY," +
			"  calzadoModel VARCHAR(15) NOT NULL," +
			" calzadoPrecio DECIMAL (7,2) NOT NULL,"+
			" ventaId BIGINT CONSTRAINT VentaId_FK REFERENCES Venta(ventaId))";
			//La tabla Calzado, recibe el ID de la venta donde esta
			
			
			stat0.executeUpdate(cad0); //crea la tabla Cliente
			stat0.executeUpdate(cad1);  //crea la tabla Vendedor
			stat0.executeUpdate(cad2);  //crea la tabla Venta
			stat0.executeUpdate(cad3);   //crea la tabla Calzado
			
			System.out.println("\n* Todas las tablas estan creadas en base de datos " +  this.nameDB);	
			
		stat0.close();
		conn.close();
		}catch(Exception ei)
		{
			ei.getStackTrace();
		}
		
	}	
}	//fin clase DataBaseV
//clase Cliente

class Boutique
{	
	//declarando mis variables.....
	private String name_Boutique;
	long clienteID, vendedorID, ventaID, calzadoId;
	String  Nombre, Telefono, Modelo, v_nombre;
	double precioNitty;
	char opcion = ' ';		
	char decision ;
	char finbucle = ' ';
	
	
	Calzado unCalzado = new Calzado(); //instanciando las clases
	Cliente unCliente = new Cliente();
	Vendedor unSeller = new Vendedor();
	Venta miVenta = new Venta();
	QueryTable miQuery = new QueryTable();
	
	
	Scanner enter = new Scanner(System.in);
	InputStreamReader isr = new InputStreamReader(System.in);
     BufferedReader br = new BufferedReader (isr);
	 
	
	public Boutique()
	{}
	
	public Boutique(String name)
	{
		this.name_Boutique = name;
		System.out.println("\n\t\t\t VAMPYSOFT  \n\t\t\t");
		System.out.println("\n");
		System.out.println("\n\t\t\t   BIENVENIDO  \n\t\t\t      A ");
		System.out.println("\t___________________________________________________________");
		System.out.printf("\n\t\t\t   %s \n", this.name_Boutique);
		System.out.println("\n\n \t KM.21 de Las Americas, Riviera del Caribe \n\n ");
		System.out.println("\t----------------------------------------------------------\n");
		
		
	}
	public void subMenuCliente()  // menu del Cliente
	{
	do{
		System.out.println("\t\t _____________________________________________________");
		System.out.printf("\n\t\t\t      M E N U  C L I E N T E  \n             ");
		System.out.println(" -----------------------------------------------------\n");
		System.out.println("\t\t *----- 1. CONSULTAR  \n");
		System.out.println("\t\t *----- 2. ELIMINAR \n"); //verificar que no necesite un UPDATE en vez de un eliminar
		System.out.println("\t\t *----- 3. ACTUALIZAR \n");
		System.out.println("\t\t *-----'n'.RETORNAR \n");
		System.out.println("\t\t-----------------------------------------------------\n");
		System.out.println(" Eliga Opcion...: ");
		opcion = enter.next().charAt(0);
		
		switch(opcion)
		   {
			   case '1':
				{
					do{
						System.out.println("\n\t\t\t\t CONSULTAR CLIENTE\n\n\n");
					System.out.print("\n\n Teclee eleccion 1-verTodos 2-solo el Id que introduzca.: ");
					char eleccion = enter.next().charAt(0);
					switch(eleccion)
					{
						 case '1':
						{
							try{
								miQuery.queryCliente();
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
							
							break;
						}
						case '2':
						{
							try
								{
									System.out.print("---ClienteID. :");
								    clienteID = enter.nextLong();
									miQuery.queryCliente((long)clienteID);
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
							break;
						}
					
					  }
					  System.out.print(" --- DESEA CONSULTAR OTRO Cliente ?? [s/n]: ");
						decision = enter.next().charAt(0);
					  
					} while(decision != 'n');
				
					break;
				}
				
				case '2':
				{
					
					do{
			     
							System.out.println("\n\t\t\t\t ELIMINAR CLIENTE\n\n");
							System.out.println("---clienteID. :");
							clienteID = enter.nextLong();
						try
						{	
							unCliente.deleteCliente((long)clienteID);
						}catch(Exception ei)
						{
							ei.getStackTrace();
						}
						System.out.print(" --- DESEA ELIMINAR OTRO CLIENTE ?? [s/n]: ");
				        decision = enter.next().charAt(0);
						
						}while(decision != 'n'); //fin do-while
					
						break;
					}//fin del case 2
					case '3':
					{
						do{
			     
							System.out.println("\n\t\t\t\t ACTUALIZAR CLIENTE\n\n");
							System.out.println("---clienteID. :");
							clienteID = enter.nextLong();
							try
						{	
							System.out.print(" --- NOMBRE       : ");
							Nombre = br.readLine();
							System.out.print(" --- TELEFONO     : ");
							Telefono = br.readLine();
							unCliente.updateCliente((long)clienteID, Nombre, Telefono);
						}catch(Exception ei)
						{
							ei.getStackTrace();
						}
						System.out.print(" --- DESEA ACTUALIZAR OTRO CLIENTE ?? [s/n]: ");
				        decision = enter.next().charAt(0);
						
						}while(decision != 'n'); //fin do-while
						break;
					}
				default: 
					System.out.println(" --- DESEA SALIR DEL MENU CLIENTE ??[4/n]: ");
				    finbucle = enter.next().charAt(0);	
		   }//fin de switch principal
		}while(finbucle != '4');
		
		
		
	}//fin metodo subMenuCliente()
	public void subMenuCalzado()  // menu del Calzado
	{
		do{
		System.out.println("\t\t _____________________________________________________");
		System.out.printf("\n\t\t\t      M E N U  C A L Z A D O  \n             ");
		System.out.println(" -----------------------------------------------------\n");
		System.out.println("\t\t *----- 1. CONSULTAR  \n");
		System.out.println("\t\t *----- 2. ELIMINAR \n");
		System.out.println("\t\t *----- 3. ACTUALIZAR \n");
		System.out.println("\t\t *----'n'. RETORNAR \n");
		System.out.println("\t\t-----------------------------------------------------\n");
		
		System.out.println(" Eliga Opcion...: ");
		opcion = enter.next().charAt(0);
		switch(opcion)
		   {
			   case '0':
				{
					do{
					System.out.println("\n\t\t\t\t Registrar CALZADO\n\n\n");
					System.out.print("\n\n \t\t DIGITE LOS DATOS DEL Calzado \n\n");
					try{
						System.out.print(" --- Calzado_ID. : ");
						calzadoId = enter.nextLong();
						System.out.print(" --- Calzado_Model. : ");
						Modelo = br.readLine();
						System.out.print(" --- Calzado_Precio. : ");
						precioNitty= enter.nextDouble();
						unCalzado = new Calzado((long)calzadoId,Modelo,precioNitty);
						unCalzado.registrarCalzado();
					}catch(Exception ei)
					{
						ei.getStackTrace();
					}
					System.out.print(" --- DESEA REGISTRAR OTRO CALZADO[s/n]: ");
					decision = enter.next().charAt(0);
					}while(decision != 'n');
				break;
				}
					
				case '1':
				{
					do{
					System.out.println("\n\t\t\t\t CONSULTAR CALZADO\n\n\n");
					System.out.print("\n\n Teclee eleccion 1-verTodos 2-solo el Id que introduzca.: ");
					char eleccion = enter.next().charAt(0);
					
					switch(eleccion)
					{
						 case '1':
						{
							try{
								miQuery.queryCalzado();
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
							
							break;
						}
						case '2':
						{
							try
								{
									System.out.print("---CalzadoID. :");
								    calzadoId = enter.nextLong();
									miQuery.queryCalzado((long)calzadoId);
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
							break;
						}
			  		
						
					}//fin switch
					System.out.print(" --- DESEA CONSULTAR OTRO Calzado ?? [s/n]: ");
						decision = enter.next().charAt(0);
					
					}while(decision != 'n'); //fin do-while
					break;
				}//fin del case 1
				
			   case'2':
			   {
				 do{
			     
				 System.out.println("\n\t\t\t\t ELIMINAR CALZADO\n\n");
			     System.out.println("---CalzadoID. :");
				 calzadoId= enter.nextLong();
				 
				 try{
					 unCalzado.deleteCalzado((long)calzadoId);
				    }catch(Exception ei)
					{
						ei.getStackTrace();
					}
						System.out.print(" --- DESEA ELIMINAR OTRO Calzado ?? [s/n]: ");
				        decision = enter.next().charAt(0);
				   }while(decision != 'n');
				   
				   break;
			   }//fin Case 2
			   case '3':
			   {
				   do{
			     
							System.out.println("\n\t\t\t\t ACTUALIZAR CALZADO\n\n");
							
							try
						{	
							System.out.print(" --- Calzado_ID : ");
						calzadoId = enter.nextLong();
						System.out.print(" --- MODELO     : ");
						Modelo = br.readLine();
						System.out.print(" --- PRECIO     : ");
						precioNitty = enter.nextDouble();
						unCalzado.actualizarCalzado((long)calzadoId,Modelo, precioNitty);
						
						}catch(Exception ei)
						{
							ei.getStackTrace();
						}
						System.out.print(" --- DESEA ACTUALIZAR OTRO CALZADO ?? [s/n]: ");
				        decision = enter.next().charAt(0);
					}while(decision != 'n'); //fin do-while
				   break;
			   }//fin case 3
			   default: 
					System.out.println(" --- DESEA SALIR DEL MENU CALZADO ??[4/n]: ");
				    finbucle = enter.next().charAt(0);

		   }//fin del switch	 principal
	   }while(finbucle !='4');
	}//fin metodo submenu-Calzado()	

	public void subMenuVendedor()  // menu Vendedor
	{
		do{
		System.out.println("\t\t _____________________________________________________");
		System.out.printf("\n\t\t\t      M E N U   V E N D E D O R  \n             ");
		System.out.println(" -----------------------------------------------------\n");
		System.out.println("\t\t *----- 1. REGISTRAR  \n");
		System.out.println("\t\t *----- 2. ELIMINAR \n");  //solo lo borrara si no tiene ventas Registradas
		System.out.println("\t\t *----- 3. ACTUALIZAR   \n");
		System.out.println("\t\t *----- 4. CONSULTAR   \n");
		System.out.println("\t\t *----'n'. RETORNAR \n");
		System.out.println("\t\t-----------------------------------------------------\n");
		
		System.out.print(" Eliga Opcion...: ");
		 opcion = enter.next().charAt(0);
		 switch(opcion)
		   {
			  case '1':
			  {
				do{
			     
					System.out.println("\n\t\t\t\t REGISTRAR VENDEDOR\n\n\n");
					System.out.println("\n\n \t\t DIGITE LOS DATOS DEL VENDEDOR \n\n");
					try{
						System.out.print(" --- VENDEDOR_ID. : ");
						vendedorID = enter.nextLong();
						System.out.print(" --- VENDEDOR_Name. : ");
						v_nombre = br.readLine();
						unSeller = new Vendedor((long)vendedorID,v_nombre);
						unSeller.registrarSeller();
					}catch(Exception ei)
					{
						ei.getStackTrace();
					}
					System.out.print(" --- DESEA REGISTRAR OTRO VENDEDOR [s/n]: ");
					decision = enter.next().charAt(0);
			 }while(decision != 'n');
			 
					break;
			   }
			   
			   case '2':
			   {
				   do{
			     
				 System.out.println("\n\t\t\t\t ELIMINAR VENDEDOR\n\n");
			     System.out.println("---VendedorID. :");
				 vendedorID = enter.nextLong();
				 
				 try{
					 unSeller.deleteVendedor(vendedorID);
				    }catch(Exception ei)
					{
						ei.getStackTrace();
					}
						System.out.print(" --- DESEA ELIMINAR OTRO VENDEDOR ?? [s/n]: ");
				        decision = enter.next().charAt(0);
				   }while(decision != 'n');
				   
				   break;
				}//fin case2	
				
				case '3':
				{
					do{
			     
					System.out.println("\n\t\t\t\t ACTUALIZAR VENDEDOR\n\n\n");
					System.out.print("---VendedorID. :");
					vendedorID = enter.nextLong();
					System.out.print(" --- VENDEDOR_Name. : ");
					try{
						v_nombre = br.readLine();
						unSeller. updateSeller((long)vendedorID, v_nombre);
						
						}catch(Exception ei)
						{
							ei.getStackTrace();
						}
						System.out.print(" --- DESEA ACTUALIZAR OTRO VENDEDOR ?? [s/n]: ");
						decision = enter.next().charAt(0);
					}while(decision != 'n');
					
					break;
				}//fin case 3
				
				case '4':
				{
					do{
										
					 System.out.println("\n\t\t\t\t CONSULTAR VENDEDOR\n\n\n");
					System.out.print("\n\n Teclee eleccion 1-verTodos 2-solo el Id que introduzca.: ");
					 char eleccion = enter.next().charAt(0);
					 switch (eleccion)
					 {
							case '1':
							{
								try{
								miQuery.queryVendedor();
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
									break;
							}
						
							case '2':
							{
								System.out.print("---VendedorID. :");
								vendedorID = enter.nextLong();
								try
								{
									miQuery.queryVendedor((long)vendedorID);
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
								break;
							}//fin switch	
					 }	
						System.out.print(" --- DESEA CONSULTAR OTRO VENDEDOR ?? [s/n]: ");
						decision = enter.next().charAt(0);
				}while(decision != 'n');
					break; //break case 3
			}//fin case  3			
				default: 
					System.out.println(" --- DESEA SALIR DEL MENU VENDEDOR ??[5/n]: ");
				    finbucle = enter.next().charAt(0);
		   	
		 }//fin switch principal 	
		}while(finbucle != '5');//fin del do-while
}
//fin menu Vendedor
 

	public void subMenuVenta() // menu de la Venta
	{
				
		do{		
		    System.out.println("\t\t _____________________________________________________");
		System.out.printf("\n\t\t\t      M E N U   V E N T A  \n             ");
		System.out.println(" -----------------------------------------------------\n");
		System.out.println("\t\t *----- 1. REALIZAR PEDIDO  \n");
		System.out.println("\t\t *----- 2. CONSULTAR VENTA \n");
		System.out.println("\t\t *----- 3. ACTUALIZAR VENTA \n"); 
		System.out.println("\t\t *----'n'. RETORNAR \n");
		System.out.println("\t\t-----------------------------------------------------\n");
		System.out.println(" Eliga Opcion...: ");
		   opcion = enter.next().charAt(0);
		   
		   switch(opcion)
		   {
			 
			case '1' :
			{
			char decision ;
			
			System.out.println("\n\t\t\t\tREALIZANDO PEDIDO\n\n\n");
			System.out.println("\n\n \t\t DIGITE LOS DATOS DEL CLIENTE \n\n");
			System.out.print(" --- CLIENTE_ID   : ");
			try{
			clienteID = enter.nextLong();
			System.out.print(" --- NOMBRE       : ");
			Nombre = br.readLine();
			System.out.print(" --- TELEFONO     : ");
			Telefono = br.readLine();
			
			unCliente = new Cliente((long)clienteID, Nombre, Telefono);
			unCliente.registrarCliente();
			 
			System.out.println("\n\n \t\t DIGITE LOS DATOS DE LA VENTA \n\n"); 
			System.out.print(" --- VENTA_ID    : ");
			ventaID = enter.nextLong();
			System.out.print(" --- VENDEDOR_ID. : ");
			vendedorID = enter.nextLong();
			System.out.print(" --- VENDEDOR_Name. : ");
			v_nombre = br.readLine();
			
			unSeller  = new Vendedor((long)vendedorID, v_nombre);
						
			}catch(Exception ei)
		    {
			  ei.getStackTrace();
		    }
			 
			System.out.print("\n\n DIGITE LOS DATOS DEL CALZADO \n\n");
			//hago un do- while para decidir cuantos cuantos calzados  agreagar a mi tabla Venta
			
			do
			{
				try{
				System.out.print(" --- Calzado_ID : ");
				calzadoId = enter.nextLong();
				System.out.print(" --- MODELO     : ");
				Modelo = br.readLine();
				System.out.print(" --- PRECIO     : ");
				precioNitty = enter.nextDouble();
				
				miVenta = new Venta (ventaID,unSeller,unCliente);	
			 
			  try{
					miVenta.registrarVenta();
			   }catch(Exception ei){ei.getStackTrace();}
				unCalzado = new Calzado((long)calzadoId,Modelo,precioNitty,miVenta);	
				unCalzado.registrarCalzado();		
				} catch(Exception ei)
		        {
			      ei.getStackTrace();
		        }
				
				System.out.print(" \n --- DESEA AGREGAR OTRO CALZADO A SU VENTA [s/n]: ");
				 decision = enter.next().charAt(0);
			 }while(decision == 's');
				
			   
				break;
			}
			case '2':
			{
				
				do
				{
					 System.out.println("\n\t\t\t\t CONSULTAR VENTA\n\n\n");
					System.out.print("\n\n Teclee eleccion 1-verTodos 2-solo el Id que introduzca.: ");
				    char eleccion = enter.next().charAt(0);
					switch(eleccion)
					{
						case '1':
						{
								try{
									miQuery.queryVenta();
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
							break;
						}
						case '2':
						{
							System.out.print("---VentaID. :");
								ventaID = enter.nextLong();
								try
								{
									miQuery.queryVenta((long)ventaID);
								}catch(Exception ei)
								{
									ei.getStackTrace();
								}
							break;
						}
					}//fin de sub switch
					System.out.print(" --- DESEA CONSULTAR OTRO VENTA ?? [s/n]: ");
						decision = enter.next().charAt(0);
				}while(decision != 'n');
				
				break;
			}//fin del case 2
			case '3':
			{
				do
				{
					 System.out.println("\n\t\t\t\t ACTUALIZAR VENTA\n\n\n");
					 System.out.print("\t Introduzca IdVenta.: ");
				     ventaID = enter.nextLong();
					 try{
					  System.out.print("\t Introduzca IdVendedor.: ");
					  vendedorID = enter.nextLong();
					  System.out.print("\t Introduzca IdCliente.: ");
					  clienteID = enter.nextLong();
					  miVenta.actualizarVenta((long)ventaID, (long)vendedorID,(long)clienteID);
				    }catch(Exception ei)
					{
						ei.getStackTrace();
					}
					System.out.print(" --- DESEA ACTUALIZAR OTRO VENTA ?? [s/n]: ");
					
				    decision = enter.next().charAt(0);
				}while(decision != 'n');
				break;
				// me actualizara la venta  con ID de la Venta, del Vendedor que vendio y con el Cliente, el cliente debe estar ya registrado
				//en la tabla Cliente
			}
			default: 
					System.out.println(" --- DESEA HACER OTRA VENTA [s/4]: ");
				finbucle = enter.next().charAt(0);
				}//fin switch	     
		  }while(finbucle != '4');
		  
		  
	    }//fin metod realizarVenta()
			
 }//fin clase Boutique

class Pantalla  // clase para El menu Principal
{
	private Boutique NittyShoe = new Boutique("NittyShoe");
	Scanner enter = new Scanner(System.in);
	char eleccion;
	public Pantalla(){}
	
	
	public void menuPrincipal()
	{
		do{
		System.out.println("\t\t _____________________________________________________");
		System.out.printf("\n\t\t\t      M E N U   P R I N C I P A L \n             ");
		System.out.println(" -----------------------------------------------------\n");
		System.out.println("\t\t *----- 1. CLIENTE  \n");
		System.out.println("\t\t *----- 2. VENDEDOR \n");
		System.out.println("\t\t *----- 3. CALZADO  \n");
		System.out.println("\t\t *----- 4. VENTA    \n");
		System.out.println("\t\t *----- 5. RETORNAR \n");
		System.out.println("\t\t-----------------------------------------------------\n");
		
		System.out.print(" Eliga Opcion...: ");
		eleccion = enter.next().charAt(0);
		switch(eleccion)
		{
			case '1':
			{
				
				NittyShoe.subMenuCliente();
				break;				
			}
			
			case '2':
			{
				NittyShoe.subMenuVendedor();
				
				break;
			}
			case '3':
			{
				NittyShoe.subMenuCalzado();
				break;
			}
			case '4':
			{
				NittyShoe.subMenuVenta();
				
				break;
			}
			
			default: 
					System.out.print(" --- DESEA SALIR DEL MENU PRINCIPAL ??[6/n]: ");
				    eleccion = enter.next().charAt(0);	
		}//fi n del switch
		
		
		}while(eleccion !='6');
	}//fin metodo menuPrincipal()
	
}//fin clase pantalla

//class Query
class QueryTable extends DataBaseV //clase de las Consultas a las tablas de VampyDB
{
	
	
	public QueryTable()
	{}
	
	//sobrecarga del metodo queryCliente()
	public void queryCliente()throws Exception
	{
			
		try{  
			  conn = DriverManager.getConnection(URL);
		  	  String query = "SELECT * FROM Cliente "; 
			  stmt = conn.prepareStatement(query);			  
			  result = stmt.executeQuery();
				long clienteID;
		    String Nombre, Telefono;
			
			System.out.println("\n\t\t\tCLIENTE \n");
			System.out.println("   ____________________________________________________");
		    System.out.println("\n  | CLI_ID      |     NOMBRE COMPLETO      | TELEFONO\n");
		    System.out.println("   ---------------------------------------------------\n");
			
			while(result.next())
			{
			   
				clienteID = result.getLong("clienteID");              
				Nombre = result.getString("clienteNombre");
				Telefono = result.getString("clienteTel");
			
				System.out.printf("|%-8s",clienteID);
				System.out.printf("      |%-8s\t",Nombre);
				System.out.printf("      |%-8s",Telefono + "\n");
           }    
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}  
			  
	}
	public void queryCliente(long id)throws Exception
	{		
		try{  
			  conn = DriverManager.getConnection(URL);
		  	  String query = "SELECT * FROM Cliente WHERE clienteID = ?"; 
			  stmt = conn.prepareStatement(query);			  
			  stmt.setLong(1,id);
			  result = stmt.executeQuery();
			
			long clienteID;
		    String Nombre, Telefono;
			
			System.out.println("\n\t\t\tCLIENTE \n");
			System.out.println("   ____________________________________________________");
		    System.out.println("\n  | CLI_ID          |   NOMBRE COMPLETO          | TELEFONO\n");
		    System.out.println("   ---------------------------------------------------\n");
			
			while(result.next()){
			   
		    clienteID = result.getLong("clienteID");              
            Nombre = result.getString("clienteNombre");
            Telefono = result.getString("clienteTel");
			
			System.out.printf("|%-8s",clienteID);
		    System.out.printf("           |%-8s\t",Nombre);
            System.out.printf("    |%-8s",Telefono + "\n");
           }    
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}
	}//fin metodo queryCliente
	//sobrecarga del metodo queryVendedor()
	public void queryVendedor()throws Exception
	{
		try{
			
		      conn = DriverManager.getConnection(URL);
		   	  String query = "SELECT * FROM Vendedor"; 
			  stmt = conn.prepareStatement(query);			  
			  
			  result = stmt.executeQuery();
			  
			   long vendedorID;
		    String Nombre;
			
			System.out.println("\n\t\t\t VENDEDOR\n");
			System.out.println("   ____________________________________________________");
		    System.out.println("\n\t\t | Vendedor_ID    |   NOMBRE COMPLETO   |\n");
		    System.out.println("   ---------------------------------------------------\n");
			
			while(result.next()){
			   
		    vendedorID = result.getLong("vendedorID");              
            Nombre = result.getString("v_nombre");
            			
			System.out.printf("\t\t  |%-8s",vendedorID);
		    System.out.printf("       | %-8s",Nombre);
			 System.out.printf("\n");
           }    
		   System.out.println("\n* Vendedor consultado en base de datos " +  this.nameDB);
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}
}//fin del queryVendedor()

	public void queryVendedor(long id)throws Exception
	{
		try{
			  conn = DriverManager.getConnection(URL);
		   	  String query = "SELECT * FROM Vendedor WHERE vendedorID = ?"; 
			  stmt = conn.prepareStatement(query);			  
			  stmt.setLong(1,id);
			  result = stmt.executeQuery();
			  
			  long vendedorID;
		      String Nombre;
			
			System.out.println("\n\t\t\t VENDEDOR\n");
			System.out.println("   ___________________________________________");
		    System.out.println("\n  | Vendedor_ID     |   NOMBRE COMPLETO   |\n");
		    System.out.println("   --------------------------------------------\n");
			
			while(result.next())
			{
			   
		    vendedorID = result.getLong("vendedorID");              
            Nombre = result.getString("v_nombre");
            			
			System.out.printf("   |%-8s",vendedorID);
		    System.out.printf("         |%-8s\t",Nombre);
           
            }    
		   System.out.println("\n* Vendedor consultado en base de datos " +  this.nameDB);
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}
	} //fin metodo queryVendedor
		
//sobrecarga del metodo queryCalzado()
public void queryCalzado()
{
	try{
		    conn = DriverManager.getConnection(URL);
		   	  String query = "SELECT * FROM Calzado "; 
			  stmt = conn.prepareStatement(query);			  
			  
			  result = stmt.executeQuery();
			  
			  long cID, venta_Id;
			  String model;
			  double precio;
			  System.out.println("\n\t\t\t CALZADOS\n");
			System.out.println(" _____________________________________________________");
		    System.out.println("\n CAL_ID    |   CAL_MODEL       |  CAL_Precio     | Venta_Id");
		    System.out.println("   ---------------------------------------------------\n");
			while(result.next())
			{
			 		   
			 cID = result.getLong("calzadoID");
			 model = result.getString("calzadoModel");
			 precio = result.getDouble("calzadoPrecio");
			 venta_Id = result.getLong("ventaId");		
			 
			 System.out.printf("  |%-8s  ",cID);
			 System.out.printf("|   %-8s",model);
		     System.out.printf("     |   %-8s ",precio);
			 System.out.printf("    |   %-8s\t",venta_Id);
			 System.out.printf("\n"); 
			}
			System.out.println("\n* Calzado consultado en base de datos " +  this.nameDB);
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}	

			  
}
public void queryCalzado(long id)throws Exception
{
		try{
		     conn = DriverManager.getConnection(URL);
		   	  String query = "SELECT * FROM Calzado WHERE calzadoID = ?"; 
			  stmt = conn.prepareStatement(query);			  
			  stmt.setLong(1,id);
			  result = stmt.executeQuery();
			  
			  long cID, venta_Id;
			  String model;
			  double precio;
			  
			  System.out.println("\n\t\t\t CALZADOS\n");
			System.out.println(" _____________________________________________________");
		    System.out.println("\n  CAL_ID     |   CAL_MODEL     |  CAL_Precio   | Venta_Id");
		    System.out.println("   ---------------------------------------------------\n");
			while(result.next())
			{
			 		   
			 cID = result.getLong("calzadoID");
			 model = result.getString("calzadoModel");
			 precio = result.getDouble("calzadoPrecio");
			 venta_Id = result.getLong("ventaId");		
						  
			  System.out.printf("  |%-8s  ",cID);
			  System.out.printf("|   %-8s",model);
		      System.out.printf("     |   %-8s ",precio);
			  System.out.printf("    |   %-8s\t",venta_Id);
			 System.out.printf("\n");
			}
			System.out.println("\n* Calzado consultado en base de datos " +  this.nameDB);
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}	

	}
	//sobrecarga del metodo queryVenta()
	public void queryVenta()throws Exception
	{
		try{
		    conn = DriverManager.getConnection(URL);
		   	  String query = "SELECT * FROM Venta"; 
			  stmt = conn.prepareStatement(query);			  
			 
			  result = stmt.executeQuery();
			  
			  long ventaID, vendedorID,clienteID;
	      	System.out.println("\n\t\t\t VENTA\n");
			System.out.println(" _____________________________________________________");
		    System.out.println("\n|  V_ID     |    Vendedor_ID   |    Cliente_ID |\n");
		    System.out.println("   ---------------------------------------------------\n");
			while(result.next()){
			 		   
			 ventaID = result.getLong("ventaId");
			 vendedorID = result.getLong("vID");
			 clienteID = result.getLong("cliID");
			
            System.out.printf("  |%-8s  ",ventaID);
			System.out.printf("|       %-8s",vendedorID);
		    System.out.printf("     |    %-8s\t",clienteID);
			System.out.printf("\n"); 
			}
			System.out.println("\n*Venta consultada en base de datos " +  this.nameDB);
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}	

		
	}
public void queryVenta(long id)
{
		try{
		    conn = DriverManager.getConnection(URL);
		   	  String query = "SELECT * FROM Venta WHERE ventaId = ?"; 
			  stmt = conn.prepareStatement(query);			  
			  stmt.setLong(1,id);
			  result = stmt.executeQuery();
			  
			  long ventaID, vendedorID,clienteID;
			  
			  System.out.println("\n\t\t\t VENTA\n");
			 System.out.println(" _____________________________________________________");
		     System.out.println("\n|  V_ID     |    Vendedor_ID   |    Cliente_ID |\n");
		     System.out.println("   ---------------------------------------------------\n");
			while(result.next()){
			 		   
			 ventaID = result.getLong("ventaId");
			 vendedorID = result.getLong("vID");
			 clienteID = result.getLong("cliID");
			
            
			 System.out.printf("  |%-8s  ",ventaID);
			System.out.printf("|       %-8s",vendedorID);
		    System.out.printf("     |    %-8s\t",clienteID);
			 System.out.printf("\n");
			 
			}
			System.out.println("\n*Venta consultada en base de datos " +  this.nameDB);
			stmt.close();
		    conn.close();
		}catch (Exception ei)
		{
			ei.getStackTrace();
		}	

	}
	
}//fin clases QueryTable

		   
class Cliente extends DataBaseV
{
 	private long clienteId;
    private String clienteNombre;
	private String clienteTel;
	
	
	public Cliente(){}
	
	public Cliente(long id, String clieN, String cliTel)
	{
		setIdCliente(id);
		setNomCliente(clieN);
		setTelefono(cliTel);
		
	}
	
	public void setIdCliente(Long id)
    {
        this.clienteId = id;
    }	
	 public void setNomCliente(String name)
    {                
		clienteNombre = name;
     }
	public void setTelefono(String tel)
	{
		  this.clienteTel= tel;
    }
	public long getId(){return this.clienteId;}
    public String getNameClient(){return this.clienteNombre;}
	public String getTelefono(){return this.clienteTel;}
	
	 public void registrarCliente() throws Exception //registrarCliente en la db
    {
		
		try
		{			
			conn = DriverManager.getConnection(URL);
				
			String cad0 = "INSERT INTO Cliente " + 
			 "( clienteID,clienteNombre,clienteTel)" +
			 "VALUES(?,?,?)";
			 
			PreparedStatement stmt = conn.prepareStatement(cad0);
			 
			 stmt.setLong(1,getId());
			 stmt.setString(2,getNameClient());
			 stmt.setString(3,getTelefono());
			 
			 stmt.executeUpdate();
			 	System.out.println("\n* Nuevo Cliente insertado en base de datos " +  this.nameDB);			
		stmt.close();
        conn.close();
		}catch(Exception ei)
		{
			ei.getStackTrace();
		}
		
	}//fin registrarCliente()
	public void updateCliente(Long Id, String name, String Tel)throws Exception		//recibira el Id del cliente que desea actualizar sus datos..
	{
		try{
			
		conn = DriverManager.getConnection(URL);
		
		String upCli = "UPDATE Cliente SET clienteNombre = ?, clienteTel = ? "
					+  " WHERE clienteID = ?";
					
		stmt = conn.prepareStatement(upCli);
		
				
		stmt.setString(1,name);
		stmt.setString(2,Tel);
		stmt.setLong(3,Id);
		stmt.executeUpdate();			
		System.out.println("\n* Cliente actualizado en base de datos " +  this.nameDB);		
		
		stmt.close();
		conn.close();
		}catch(Exception eo)
		{
			eo.getStackTrace();
		}
	}//fin metodo updateCliente()
	
	public void deleteCliente(long Id)throws Exception
	{
		try{
			conn = DriverManager.getConnection(URL);
			String delCli = "DELETE FROM Cliente WHERE clienteID = " + Id;
			
			stmt = conn.prepareStatement(delCli);		
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			System.out.println("\n* Cliente borrado de la base de datos " +  this.nameDB);		
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}//fin metodo deleteCliente()	
 }//fin clase Client
 
//class Vendedor
class Vendedor extends DataBaseV
{
	private long Id;
	private String nombreVendedor;

	public Vendedor(){}
	
	public Vendedor(long Id)
	{this.Id = Id;}
	
	public Vendedor(long Id, String name)
	{
		setIDVendedor(Id);
		setNameSeller(name);
	}
	
	public void setIDVendedor(long id)
	{
		this.Id = id;
	}
	
	public void setNameSeller(String name)
	{
		this.nombreVendedor = name;
	}
	
	public long getIdSeller(){return this.Id;}
	public String getNameSeller(){return this.nombreVendedor;}
	
	public void registrarSeller()throws Exception
	{
		try
		{
			conn = DriverManager.getConnection(URL);
			String cad0 = "INSERT INTO Vendedor " + 
			 "( vendedorID,v_nombre)" +
			 "VALUES(?,?)";
			stmt = conn.prepareStatement(cad0);
			
			stmt.setLong(1,getIdSeller());
			stmt.setString(2,getNameSeller());
			 
			stmt.executeUpdate();
			
			System.out.println("\n* Nuevo Vendedor insertado en base de datos " +  this.nameDB);			
			
			stmt.close();
           conn.close();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}//fin metodo registrarSeller()
	public void updateSeller(Long Id, String name)throws Exception	 //probar
	{
		conn = DriverManager.getConnection(URL);
		
		String upSeller = "UPDATE Vendedor SET v_nombre = ? WHERE vendedorID  = ?";
		stmt = conn.prepareStatement(upSeller);
		
		
		stmt.setString(1,name);
		stmt.setLong(2,Id);
		stmt.executeUpdate();			
		System.out.println("\n* Vendedor actualizado en base de datos " +  this.nameDB);		
		
		stmt.close();
		conn.close();
	}//fin metodo updateSeller()
	
	public void deleteVendedor(long Id)throws Exception //probar
	{
		try{
			conn = DriverManager.getConnection(URL);
			String delSeller = "DELETE FROM Vendedor WHERE vendedorID = ? ";
			
			stmt = conn.prepareStatement(delSeller);
			stmt.setLong(1,Id);
			stmt.executeUpdate();
			System.out.println("\n* Vendedor borrado de la  base de datos " +  this.nameDB);		
			stmt.close();
			conn.close();
			
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}//fin metodo deleteVendedor
	
	
}//fin clase Vendedor

class Calzado extends DataBaseV
{
	private long calId;
	private String modelo;
	private double precio;
	private Venta miVenta; //Calzado recibe un objeto Venta, agregacion
	
	public Calzado(){}
	public Calzado(long calId, String model, double precio)
	{
		setID(calId);
		setModelo( model );
		setPrecio( precio);
	}
	public Calzado(long calId, String model, double precio, Venta miVenta)
	{
		setID(calId);
		setModelo( model );
		setPrecio( precio);
		this.miVenta = miVenta;
	}
	
	public void setID(long Id)
	{
		this.calId = Id;
	}
	public void setModelo(String model)
	{
		this.modelo = model;
	}
	public void setPrecio(double price)
	{
		this.precio = (price > 0) ? price : 0.00;
	}
	
	public long getId(){return this.calId;}
	public String getModelo(){return this.modelo;}
	public double getPrecio(){return this.precio;}
	
	public void registrarCalzado() throws Exception
	{
		try{
		conn = DriverManager.getConnection(URL);
		String cad0 = "INSERT INTO Calzado " + 
			 "( calzadoID,calzadoModel,calzadoPrecio,ventaId)" +
			 "VALUES(?,?,?,?)";
			 
			stmt = conn.prepareStatement(cad0);
			
			stmt.setLong(1,getId());
			stmt.setString(2,getModelo());
			stmt.setDouble(3,getPrecio());
			stmt.setLong(4,miVenta.getIdVenta());
			
			stmt.executeUpdate();
			
			System.out.println("\n* Nuevo Calzado insertado en base de datos " +  this.nameDB);
			stmt.close();
			conn.close();		
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}//fin registrarCalzado()
	//recibe el Id del Calzado que quiere actualizar 
	public void actualizarCalzado(long id, String model, double precio)throws Exception
	{
		try
		{
			conn = DriverManager.getConnection(URL);
		
		   String upCal = "UPDATE Calzado SET calzadoModel = ?, calzadoPrecio = ? "
					+  " WHERE calzadoID  = " + id;
					
			stmt = conn.prepareStatement(upCal);
			
		stmt.setString(1,model);
		stmt.setDouble(2,precio);
		stmt.executeUpdate();			
		System.out.println("\n* Calzado actualizado en base de datos " +  this.nameDB);		
		
		stmt.close();
		conn.close();
		}catch(Exception eo)
		{
			eo.getStackTrace();
		}
	}//fin metodo actualizarCalzado()
	
	public void deleteCalzado(long Id)throws Exception  //probar
	{
		try{
		  conn = DriverManager.getConnection(URL);
			String delCal = "DELETE FROM Calzado WHERE calzadoID = " + Id;
			
			stmt = conn.prepareStatement(delCal);		
			stmt.executeUpdate();
					System.out.println("\n* Calzado borrado de base de datos " +  this.nameDB);	
			stmt.close();
			conn.close();
			
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}//fin metodo de deleteCalzado()

  
}//fin class Calzado

class Venta extends DataBaseV 		//class Venta solo guarda el Id del Vendedor y del Cliente que compro en NittyShoe Boutique
{	
	private long IdVenta;
	private Vendedor miV ;
	private Cliente unCli;
	
	
	public Venta(){}
	
	public Venta(long IdVenta, Vendedor miV, Cliente unCli)
	{
		this.IdVenta = IdVenta;
		this.unCli = unCli;
		this.miV= miV;
	}
	
	public long getIdVenta()  {return IdVenta;}
	
	public void registrarVenta()throws Exception  //probar
	{
		
		try{
			conn = DriverManager.getConnection(URL);
			
			String setVenta = "INSERT INTO Venta" + 
			 "( ventaId, vID,cliID)" +
			 "VALUES(?,?,?)";
		
		 stmt = conn.prepareStatement(setVenta);
			
            stmt.setLong(1,getIdVenta());
			stmt.setLong(2,miV.getIdSeller());
			stmt.setLong(3,unCli.getId());
		
			
			System.out.println("\n* Venta Registrada en base de datos " +  this.nameDB);
			
			stmt.executeUpdate();
					
			stmt.close();
			conn.close();
			
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
	}//registar Venta
public void actualizarVenta(long IdVenta,long IdVendedor,long IdCliente)throws Exception
{
	try{
			
		conn = DriverManager.getConnection(URL);
		String upVent = "UPDATE Venta SET  vID = ?, cliID = ? "
					+  " WHERE ventaId = ?";
					
		stmt = conn.prepareStatement(upVent);
		stmt.setLong(1,IdVendedor);
		stmt.setLong(2,IdCliente);
		stmt.setLong(3,IdVenta);
		stmt.executeUpdate();			
		System.out.println("\n* Venta  actualizada en base de datos " +  this.nameDB);		
		
		stmt.close();
		conn.close();
		}catch(Exception eo)
		{
			eo.getStackTrace();
		}
	}
		



  }//fin clase Venta



public class VampySoft
{
    public static void main(String[] args)
    {	  		
  		
     try{  
		//controlando la excepcion 
		 DataBaseV miBD = new DataBaseV();  
		 miBD.crearDB();      //crear mi Base de Datos
		 miBD.crearTablas();  //creando mis tablas
	  
		Pantalla miP = new Pantalla(); 
		miP.menuPrincipal();  //menu que utiliza todos los metodos de las clases creadas
	 
	  }catch(Exception ei)
	  {
		  ei.getStackTrace();
	  }
	  
    }    
}
