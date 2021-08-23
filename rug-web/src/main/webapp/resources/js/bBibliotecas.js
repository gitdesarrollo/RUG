//***************************************************************
//VALIDACION DE DATOS OBLIGATORIOS PARA LAS FORMAS DE CAPTURA
//***************************************************************

arregloCampos = new Array();

//VARIABLES GLOBALES
sCamposVacios = "";
arregloCampos = new Array();
var iPos = 0;

//PSEUDO OBJETO CAMPO
function campo(ControlNombre, Etiqueta){
	this.ControlNombre = ControlNombre;
	this.Etiqueta = Etiqueta;
}

//FUNCION QUE AGREGA LOS CAMPOS A UN ARREGLO
function fAgregaCampo(ControlNombre, Etiqueta){
	
	//DEFINIMOS EL OBJETO DE REPLAZOS
	function Remplazo(CodigoEscape, CodigoNormal){
		this.CodigoEscape = CodigoEscape;
		this.CodigoNormal = CodigoNormal;					
	}

	//CREAMOS EL ARREGLO QUE CONTIENE LOS OBJETOS REMPLAZO
	arregloRemplazo = new Array();
	
	//CREAMOS LOS OBJETOS QUE REMPLAZAN LAS VOCALES
	arregloRemplazo[0]  = new Remplazo ("&aacute;", "�");
	arregloRemplazo[1]  = new Remplazo ("&eacute;", "�");
	arregloRemplazo[2]  = new Remplazo ("&iacute;", "�");
	arregloRemplazo[3]  = new Remplazo ("&oacute;", "�");
	arregloRemplazo[4]  = new Remplazo ("&uacute;", "�");
	arregloRemplazo[5]  = new Remplazo ("&Aacute;", "�");
	arregloRemplazo[6]  = new Remplazo ("&Eacute;", "�");
	arregloRemplazo[7]  = new Remplazo ("&Iacute;", "�");
	arregloRemplazo[8]  = new Remplazo ("&Oacute;", "�");
	arregloRemplazo[9]  = new Remplazo ("&Uacute;", "�");
	arregloRemplazo[10] = new Remplazo ("&amp;", "&");
	arregloRemplazo[11] = new Remplazo ("&ntilde;", "�");
	arregloRemplazo[12] = new Remplazo ("&Ntilde;", "�");

	//ITERAMOS EL ARREGLO
	for(i=0; i<arregloRemplazo.length; i++){
		// SI CONTIENE ALG�N C�DIGO DE ESCAPE DEL ARREGLO SE REMPLAZA
		if(Etiqueta.indexOf(arregloRemplazo[i].CodigoEscape)){
			Etiqueta = Etiqueta.replace(arregloRemplazo[i].CodigoEscape, arregloRemplazo[i].CodigoNormal);
		}			
	}

	//LLENAMOS EL ARREGLO DE CAMPOS
	arregloCampos [iPos] = new campo (ControlNombre, Etiqueta);
	iPos++;
}

//FUNCION QUE REVISA LOS CAMPOS VACIOS
function fRevisaCampos(){
	var bExito = false;
	for(var i=0; i<arregloCampos.length; i++)
	{
		 	
		    //RECORREMOS EL CAMPO PARA VER SI SOLO TRAE COMO VALOR ESPACIOS EN BLANCO.
			var espacios=true;
			var cont = 0;
			var campo= document.frmRegistro.elements[arregloCampos[i].ControlNombre].value;
			while (espacios && (cont < campo.length)) {
				if (campo.charAt(cont) != " ") {
					espacios = false;
				}
				cont++;
			}
			
			//VERIFICAMOS QUE EL ELEMENTO DEL FORMULARIO ESTE VACIO O TENGA SOLO ESPACIOS EN BLANCO.
			if(document.frmRegistro.elements[arregloCampos[i].ControlNombre].value == "" || espacios){	
			//AGREGAMOS LA ETIQUETA DEL CAMPO A LA CADENA 
			sCamposVacios += arregloCampos[i].Etiqueta;

			//SEPARAMOS POR COMAS LOS ELEMENTOS
			sCamposVacios += ", ";
		}
	}
	
	//LA ULTIMA COMA LA CAMBIAMOS POR UN PUNTO.
	iUltimaComa = sCamposVacios.lastIndexOf(", ");
	sCamposVacios = sCamposVacios.substring(0, iUltimaComa);
	sCamposVacios += ".";
	
	if (sCamposVacios != "."){
		//alert("Los siguientes campos son obligatorios: " + sCamposVacios)
	}else{
		bExito = true;
	}	
	
	sCamposVacios = "";
	return bExito;
}

/******************************************************************************
DESCRIPCION: VALIDA QUE EN UN CAMPO DE TEXTO SE HAYAN INTRODUCIDO SOLO NUMEROS
Y EL PUNTO DECIMAL
LLAMADA: ESTA FUNCION SE DEBERA COLOCAR EN EL EVENTO onKeydown
******************************************************************************/
function SoloNumerosDecimal(){
	//VERIFICA SI SE HA INTRODUCIDO UN CARACTER DISTINTO A UN NUMERO
	if (event.keyCode < 48 || event.keyCode > 57){
		//alert("entro" + event.keyCode);
		
		//VERIFICA SI SE HA INTRODUCIDO UN CARACTER DISTINTO A UN NUMERO
		if (event.keyCode < 96 || event.keyCode > 105){
		
			//TECLA DE BORRADO Y SUPRIMIR
			if (event.keyCode == 8 || event.keyCode == 46){
				return;
			}
			//TECLAS DE CURSOR IZQUIERDO Y DERECHO
			if (event.keyCode == 37 || event.keyCode == 39){
				return;
			}			
			//TECLA TABULADOR
			if (event.keyCode == 9){
				return;
			}
			//TECLA SHIFT Y ALT
			if (event.keyCode == 16|| event.keyCode == 18){
				//alert("TECLA NO PERMITIDA");
				event.returnValue = false;
			}
			//PUNTO DECIMAL
			if (event.keyCode == 190){
				return;
			}
			//PUNTO DECIMAL TECLADO NUMERICO
			if (event.keyCode == 110){
				return;
			}

			//CUALQUIER OTRA TECLA NO ES PERMITIDA
			event.returnValue = false;
		}	
	}
	
}



/******************************************************************************
DESCRIPCION: VALIDA QUE EN UN CAMPO DE TEXTO SE HAYAN INTRODUCIDO SOLO NUMEROS
LLAMADA: ESTA FUNCION SE DEBERA COLOCAR EN EL EVENTO onKeydown
******************************************************************************/
function SoloNumeros(){
	//VERIFICA SI SE HA INTRODUCIDO UN CARACTER DISTINTO A UN NUMERO
	if (event.keyCode < 48 || event.keyCode > 57){
		//alert("entro" + event.keyCode);
		
		//VERIFICA SI SE HA INTRODUCIDO UN CARACTER DISTINTO A UN NUMERO
		if (event.keyCode < 96 || event.keyCode > 105){
		
			//TECLA DE BORRADO Y SUPRIMIR
			if (event.keyCode == 8 || event.keyCode == 46){
				return;
			}
			//TECLAS DE CURSOR IZQUIERDO Y DERECHO
			if (event.keyCode == 37 || event.keyCode == 39){
				return;
			}			
			//TECLA TABULADOR
			if (event.keyCode == 9){
				return;
			}
			//TECLA SHIFT Y ALT
			if (event.keyCode == 16|| event.keyCode == 18){
				//alert("TECLA NO PERMITIDA");
				event.returnValue = false;
			}
			//CUALQUIER OTRA TECLA NO ES PERMITIDA
			event.returnValue = false;
		}	
	}
	
}

/******************************************************************************
DESCRIPCION: VALIDA QUE EN UN CAMPO DE TEXTO SE HAYAN INTRODUCIDO SOLO LETRAS(INCLUYENDO LA "�" Y EL ESPACIO)
LLAMADA: ESTA FUNCION SE DEBERA COLOCAR EN EL EVENTO onKeydown
******************************************************************************/
function SoloLetras(){
	
	//HALLAMOS EL OBJETO
	oInputName = window.event.srcElement.name;
	sValue     = document.all[oInputName].value;
		
	iCodigoAscii = sValue.charCodeAt(sValue.length-1);
	
	//REVISA QUE EL VALOR DEL ULTIMO CARACTER SE ENCUENTRE EN EL RANGO ADECUADO
	if((iCodigoAscii > 64 && iCodigoAscii < 91) || (iCodigoAscii > 96 && iCodigoAscii < 123) || (iCodigoAscii == 209) || (iCodigoAscii == 32)){
		//TODO BIEN
	}else{
		sValue = document.all[oInputName].value.substring(0, document.all[oInputName].value.length-1);						
		document.all[oInputName].value = sValue;
	}
	
	//ASIGNAMOS DINAMICAMENTE UNA FUNCION AL EVENTO ONBLUR
	
	document.all[oInputName].onblur = SoloNumerosLetras;
}

/******************************************************************************
DESCRIPCION: VALIDA QUE EN UN CAMPO DE TEXTO SE HAYAN INTRODUCIDO SOLO NUMEROS O LETRAS(INCLUYENDO LA "�" Y EL ESPACIO)
LLAMADA: ESTA FUNCION SE DEBERA COLOCAR EN EL EVENTO onKeydown
******************************************************************************/
function SoloNumerosLetras(){
	
	//HALLAMOS EL OBJETO
	oInputName = window.event.srcElement.name;
	sValue     = document.all[oInputName].value;
		
	iCodigoAscii = sValue.charCodeAt(sValue.length-1);
	
	//REVISA QUE EL VALOR DEL ULTIMO CARACTER SE ENCUENTRE EN EL RANGO ADECUADO
	if((iCodigoAscii > 47 && iCodigoAscii < 58) || (iCodigoAscii > 64 && iCodigoAscii < 91) || (iCodigoAscii > 96 && iCodigoAscii < 123) || (iCodigoAscii == 209) || (iCodigoAscii == 32)){
		//TODO BIEN
	}else{
		sValue = document.all[oInputName].value.substring(0, document.all[oInputName].value.length-1);						
		document.all[oInputName].value = sValue;
	}
	
	//ASIGNAMOS DINAMICAMENTE UNA FUNCION AL EVENTO ONBLUR
	
	document.all[oInputName].onblur = SoloNumerosLetras;
}

function SonLetrasNumeros(nPosInicio, nPosFinal, Objeto){
var lValido = true;
var d = 0;
	/* Valida que sean num�ricos */
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  //alert(String.fromCharCode(d));
	  if ((d >= 48) && (d <= 57)) {
		d = 57 - (d -48);
	  } //  Termina if
	  else {
	  	// SON LETRAS MAYUSCULAS
	  	if ((d >= 65) && (d <= 90)){
		}
		else {
		 	lValido = false;
		}
	  } // Termina else
	} // Termina for

  if (!lValido){
	 return(false);
  }
  return(true);
}


function SonLetrasCaracteresValidos(nPosInicio, nPosFinal, Objeto){
var lValido = true;
var d = 0;
var nBlancos = 0;
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  // Si son may�sculas
	  if ((d >= 65) && (d <= 90)) {
	  }
	  else {
	     // Si (�=209) (@=64) (&=38)
	     if ((d == 209) || (d == 64) || (d == 38)) {
	     }
		 else {
			lValido = false;
		 } // Termina else
	  } // Termina else
	} // Termina for
	
    if (!lValido){
	   return(false);
	}
	return(true);
}


function SonNumeros(nPosInicio, nPosFinal, Objeto){
var lValido = true;
var d = 0;
	/* Valida que sean num�ricos */
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  //alert(String.fromCharCode(d));
	  if ((d >= 48) && (d <= 57)) {
		d = 57 - (d -48);
	  } //  Termina if
	  else {
		 lValido = false;
	  } // Termina else
	} // Termina for

  if (!lValido){
	 return(false);
  }
  return(true);
}

function SonLetras(nPosInicio, nPosFinal, Objeto, bAcentos){
var lValido = true;
var d = 0;
var nBlancos = 0;
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  //alert(String.fromCharCode(d));
	  // Si son may�sculas
	  if ((d >= 65) && (d <= 90)) {
	  }
	  else {
	     // Si son min�sculas
	     if ((d >= 97) && (d <= 122)) {
	     }
		 else {
		    // S�lo se ejecuta si bAcentos es true, es decir, debe validar acentos? Si
            if (bAcentos) {
				// Son letras con acento (225 - �, 233 - �, 237 - �, 243 - �, 250 - �, 241 - �, 209 - �, 193 - �, 201 - �, 205 - �, 211 - �, 218 - �)
				if ((d ==225) || (d==233) || (d==237) || (d==243) || (d==250) || (d==241) || (d==209) || (d==193) || (d==201) || (d==205) || (d==211) || (d==218)) {
				}
				else {
					if(d==32) {
					   nBlancos++;
					}
					else {
					   lValido = false;
					} // Termina else
				} // Termina else			
			} // Termina if
			else {
		   		lValido = false;
			} // Termina else
		 } // Termina else
	  } // Termina else
	} // Termina for
	
    if (!lValido){
	   return(false);
	}
	return(true);
}


function EsMailValido(Objeto){
var nLongitud = 0;
var nNumeroArroba = 0;
var nNumeroPuntos = 0;
var cCadenaAux = "";
var bValido = true;

    // Se quitan los espacios en blanco del e-mail
    Objeto.value = AllTrim(Objeto);

   // Si no hay arrobas o puntos en el texto regresa falso
   if ((Objeto.value.indexOf('@', 0) == -1) || (Objeto.value.indexOf('.', 0) == -1)) {
      return(false);
   }
   else{
      // Obtiene la cadena de caracteres hasta antes del arroba
      cCadenaAux = Objeto.value.substring(0,Objeto.value.indexOf('@', 0));
      nLongitud = cCadenaAux.length;
	  // Si la longitud de la cadena de caracteres hasta antes del arroba es menor a cero
      if (nLongitud<=0){
        return(false);
	  }
	  else {
	     // Verifica que la primera cadena antes del arroba no tenga puntos
  	     if (cCadenaAux.indexOf('.', 0) > 0){
            return(false);
		 }
		 else {
		      // Obtiene la cadena de caracteres despu�s del arroba
			  cCadenaAux = Objeto.value.substring(Objeto.value.indexOf('@', 0)+1,Objeto.value.indexOf('.', 0));
			  nLongitud = cCadenaAux.length;
			  // Si la longitud de la cadena de caracteres despu�s del arroba es menor a cero
			  if (nLongitud<=0){
				 return(false);
			  }
			  else {
				 // Verifica que la cadena de dominio no tenga arrobas despu�s de la primera arroba.
				 if (cCadenaAux.indexOf('@', 0) > 0){
					return(false);
				 }
				 else {
				      // Obtiene la cadena de caracteres despu�s del punto
					  cCadenaAux = Objeto.value.substring(Objeto.value.indexOf('.', 0)+1,Objeto.value.length);
					  nLongitud = cCadenaAux.length;
					  // Si la longitud de la cadena de caracteres despu�s del punto es menor a cero
					  if (nLongitud<=0){
						 return(false);
					  } // Termina if
					  else {
					     // Verifica que la cadena despu�s del punto no tenga arrobas.
						 if (cCadenaAux.indexOf('@', 0) > 0){
							//alert ("La cadena no puede tener @ despu�s del punto");
							return(false);
						 } // Termina if
					  } // Termina else
			     } // Termina else
			  } // Termina else

		 } // Termina else
	  
	  } // Termina else
   } // Termina else
   return(true);
}

function AllTrim(palabra){
	var sResult = "";
	for(i=0; i < palabra.value.length; i++){
		if (palabra.value.substring(i,i+1)!=" "){
			sResult=sResult+palabra.value.substring(i,i+1);
		}
	}
	return(sResult);
}


function ValidaFecha(Cadena){
	var Fecha= new String(Cadena)	// Crea un string
	// Cadena A�o
	var Ano= new String(Fecha.substring(Fecha.lastIndexOf("/")+1,Fecha.length))
	// Cadena Mes
	var Mes= new String(Fecha.substring(Fecha.indexOf("/")+1,Fecha.lastIndexOf("/")))
	// Cadena D�a
	var Dia= new String(Fecha.substring(0,Fecha.indexOf("/")))

	// Valido el a�o
	if (isNaN(Ano) || Ano.length<4 || parseFloat(Ano)<1900){
		Cadena = "";
	}
	else {
		// Valido el Mes
		if (isNaN(Mes) || parseFloat(Mes)<1 || parseFloat(Mes)>12){
			Cadena = "";
		}
		else {
			// Valido el Dia
			if (isNaN(Dia) || parseInt(Dia)<1 || parseInt(Dia)>31){
				Cadena = "";
			}
		}
	}
	return (Cadena);
}

function Cantidades(nPosInicio, nPosFinal, Objeto){
var lValido = true;
var d = 0;
var dot = 0;
	/* Valida que sean n�meros o puntos */
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  //alert(String.fromCharCode(d));
	  if ((d >= 48) && (d <= 57)||(d==46)) {
		if(d==46){
			dot = dot + 1;
		}
		//quien sabe para que es este rengl�n --> d = 57 - (d -48);
	  } //  Termina if
	  else {
		 lValido = false;
	  } // Termina else
	} // Termina for

  if (!lValido){
	 return(false);
  }
  if(dot > 1){
  	return(false);
  }
  return(true);
}

function TodosSinComillas(nPosInicio, nPosFinal, Objeto){
var lValido = true;
var d = 0;
	/* Valida que sea cualquier caracter sin comillas */
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  //alert(String.fromCharCode(d));
	  if ( (d == 34) || (d ==39) ) {
		lValido = false;
	  } //  Termina if
	  
	} // Termina for

  if (!lValido){
	 return(false);
  }
  return(true);
}

function SonLetrasSinComillas(nPosInicio, nPosFinal, Objeto, bAcentos){
var lValido = true;
var d = 0;
var nBlancos = 0;
	/* Valida que sean solo letras sin comillas */
	for (i=nPosInicio; i<nPosFinal; i++) {
	  d = Objeto.value.charCodeAt(i);
	  //alert(String.fromCharCode(d));
	  
	  // Si es comilla o ap�strofo
	  if ( (d == 34) || (d ==39) ) {
		lValido = false;

	  }
	  // Si son may�sculas
	  if ((d >= 65) && (d <= 90)) {
	  }
	  else {
	     // Si son min�sculas
	     if ((d >= 97) && (d <= 122)) {
	     }
		 else {
		    // S�lo se ejecuta si bAcentos es true, es decir, debe validar acentos? Si
            if (bAcentos) {
				// Son letras con acento (225 - �, 233 - �, 237 - �, 243 - �, 250 - �, 241 - �, 209 - �, 193 - �, 201 - �, 205 - �, 211 - �, 218 - �)
				if ((d ==225) || (d==233) || (d==237) || (d==243) || (d==250) || (d==241) || (d==209) || (d==193) || (d==201) || (d==205) || (d==211) || (d==218)) {
				}
				else {
					if(d==32) {
					   nBlancos++;
					}
					else {
					   lValido = false;
					} // Termina else
				} // Termina else			
			} // Termina if
			else {
		   		lValido = false;
			} // Termina else
		 } // Termina else
	  } // Termina else
	} // Termina for
    if (!lValido){
	   return(false);
	}
	return(true);
}



//***************************************************************
//ABRE UNA URL
//ORIGEN: DREAMWAVER
//***************************************************************
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

//***************************************************************
//ABRE UNA PAGINA EN UNA VENTANA NUEVA
//ORIGEN: DREAMWAVER
//***************************************************************
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

//***************************************************************
//ABRE UNA PAGINA EN UNA VENTANA NUEVA PARA REPORTES
//***************************************************************
function MM_openBrWindowRep(theURL,winName,features) { //v2.0
	OpenWindow=window.open("", winName, features);
	OpenWindow.location = theURL;
}

//***************************************************************
//VALIDA LETRAS Y ESPACIOS
//***************************************************************
function ValidaLetrasYEspacios(PsCadena){
	var SsValue=PsCadena;
	var ScChar=null;
	var SsValidos='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ';
	for(i=0; i!=SsValue.length; i++) {
		 ScChar=SsValue.substring(i,i+1)
		 //VERIFICA QUE INGRESE SOLO LETRAS Y ESPACIOS
		 if (SsValidos.indexOf(ScChar) == -1) {
			  //alert(MENSAJE_079);
			  return(false);
		 }//EXISTE SOLO LETRAS Y ESPACIOS
	}
	return(true);
}

//***************************************************************
//FUNCION VALIDA SOLO LETRAS SIN ESPACIOS
//***************************************************************
function ValidaSoloLetras(PsCadena){
	var SsValue=PsCadena;
	var ScChar=null;
	var SsValidos='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	for(i=0; i!=SsValue.length; i++) {
		 ScChar=SsValue.substring(i,i+1)
		 //VERIFICA QUE SE INGRESE LETRAS SIN ESPACIOS
		 if (SsValidos.indexOf(ScChar) == -1) {
			  //alert(MENSAJE_080);
			  return(false);
		 }//EXISTE LETRAS SIN ESPACIOS
	}
	return(true);
}

/******************************************************************************
DESCRIPCION: AGREGA UN ELEMENTO A UN CONTROL TIPO SELECT

PARAMETROS:
oControl: OBJETO SELECT AL QUE SE LE VAN AGREGAR OPCIONES
sTexto: EL TEXTO DE LA OPCION
sValor: EL VALOR DE LA OPCION
iPosicion: LA POSICION DONDE SE COLOCARA LA OPCION DENTRO DEL SELECT
******************************************************************************/
function AgregaSelectOpcion(oControl, sTexto, sValor, iPosicion){
	var oOption = document.createElement("OPTION");
	oOption.text=sTexto;
	oOption.value=sValor;
	oControl.options.add(oOption,iPosicion);
}

/******************************************************************************
DESCRIPCION: BUSCA UN ELEMENTO EN UN CONTROL SELECT Y LO SELECCIONA

PARAMETROS:
oControl: OBJETO SELECT EN DONDE SE VA A BUSCAR EL VALOR
sValor: EL VALOR A BUSCAR
******************************************************************************/
function BuscaSelectOpcion(oControl, sValor){
	var iOpcion= 0;
	var bEncontrado = false;
	while (iOpcion < oControl.options.length){
		if (oControl.item(iOpcion).value == sValor){
			oControl.selectedIndex = iOpcion;
			bEncontrado = true;
		}
		iOpcion++;
	}
	return bEncontrado;
}

/******************************************************************************
DESCRIPCION: SELECCIONA TODAS LAS OPCIONES DE UN OBJETO SELECT

PARAMETROS:
oControl: OBJETO SELECT EN DONDE SE VAN A SELECCIONAR LAS OPCIONES

NOTAS: EL OBJETO SELECT DEBE TENER LA OPCION "MULTIPLE" HABILITADA
******************************************************************************/
function SeleccionaSelectOpcion(oControl, sValor){
	for (iOpcion = 0; iOpcion < oControl.options.length; iOpcion++){
		oControl.options(iOpcion).selected = true;
	}
}

/******************************************************************************
DESCRIPCION: SELECCIONA DE MANERA ALEATORIA UN BACKGROUND PARA PEQUESCAMPEONES
AUTOR: GIANNINO ALVAREZ		21/OCT/2003

PARAMETROS:
SIN PARAMETROS
******************************************************************************/
function fPintaBgPeques(){
	bgArray = new Array();
	bgArray[0] = "bgPequesImagenMontana.gif";
	bgArray[1] = "bgPequesImagenCasas.gif";
	bgArray[2] = "bgPequesImagenPeces.gif";
	bgArray[3] = "bgPequesImagenTravesura.gif";
	bgArray[4] = "bgPequesImagenBolitas.gif";
	
	iRandom = Math.random();
	
	iNumero = Math.round (iRandom * ((bgArray.length)-1));
	
	return bgArray[iNumero];
}


/******************************************************************************
DESCRIPCION: POSICIONA EL PIE DE PAGINA
AUTOR: GIANNINO ALVAREZ		21/OCT/2003

PARAMETROS:
SIN PARAMETROS
******************************************************************************/	
function fPosicionaPie(){
	HVal = getViewportHeight();
	if(document.getElementById("FinMenu")){
		if(document.getElementById("Fin").offsetTop + 100 < HVal) {
			document.getElementById("PiePagina").style.top = HVal - 100 + 'px';	
								
		}else{
			if(document.getElementById("FinMenu").offsetTop > HVal) {
				document.getElementById("PiePagina").style.top = document.getElementById("FinMenu").style.top;
			}	
		}			
	}else{
		if(document.getElementById("Fin").offsetTop + 100 < HVal) {
			document.getElementById("PiePagina").style.top = HVal - 100 + 'px';							
		}
	}
}

/******************************************************************************
DESCRIPCION: OBTIENE EL ALTO DE PANTALLA DEL BROWSER (VIEWPORT)
AUTOR: GIANNINO ALVAREZ		21/OCT/2003

PARAMETROS:
SIN PARAMETROS
******************************************************************************/				
function getViewportHeight() {
    // supported in Mozilla, Opera, and Safari
     if(window.innerHeight)
         return window.innerHeight;
	
    // supported in standards mode of IE, but not in any other mode
     if(window.document.documentElement.clientHeight)
         return document.documentElement.clientHeight;
	
    // supported in quirks mode, older versions of IE, and mac IE (anything else).
   
    return window.document.body.clientHeight;
}
/******************************************************************************
FUNCION QUE TRUNCA EL VALOR DE UN TEXTAREA AL N�MERO DE CARACTERES
QUE SE LE ENVIAN EN EL PARAMETRO NUMCHARS
******************************************************************************/	
function limitChars(numChars){
 if(event.propertyName != "value") return;
 	var oTextArea = event.srcElement;
 	var currTextLength = oTextArea.value.length;
 	if(currTextLength > numChars){
  		// truncate TextArea
  		oTextArea.value = oTextArea.value.substring(0, numChars);
 	}
}



/******************************************************************************
DESCRIPCION: CONVIERTE EN MAYUSCULAS EL CONTENIDO DE UN INPUT="TEXT" Y UN TEXTAREA
AUTOR: GIANNINO ALVAREZ		12/NOV/2003

PARAMETROS: SIN PARAMETROS
******************************************************************************/		


//FUNCION QUE SE DISPARA EN EL EVENTO onKeyUp
function fOnKeyUp(evt){	
	
	//IDENTIFICAMOS EL OBJETO QUE DISPARA EL EVENTO 
	if (document.all){
		Elemento = window.event.srcElement;
	}else{
		Elemento = evt.target;
	}
	
	if (Elemento.type != "text" && Elemento.type != "textarea"){
		return;
	}
	
	//VERIFICAMOS QUE NO SE ENCUENTRE EN LAS EXCEPCIONES
	if(Elemento.name == "j_username")return;	
	if(Elemento.name == "MeEmail1")return;	
	if(Elemento.name == "MeEmail2")return;	
	if(Elemento.name == "MeEmail3")return;	
	if(Elemento.name == "MeEmail4")return;	
	if(Elemento.name == "MeEmail5")return;	
	if(Elemento.name == "MrEmail1")return;	
	if(Elemento.name == "MrEmail2")return;	
	if(Elemento.name == "MrEmail3")return;	
	if(Elemento.name == "MrEmail4")return;	
	if(Elemento.name == "MrEmail5")return;	
	if(Elemento.name == "SuEmail1")return;	
	if(Elemento.name == "SuEmail2")return;	
	if(Elemento.name == "SuEmail3")return;	
	if(Elemento.name == "SuEmail4")return;	
	if(Elemento.name == "SuEmail5")return;	
	if(Elemento.name == "CveUsuario")return;	
	if(Elemento.name == "EMail")return;	
	
	//SE MODIFICA EL VALUE DEL OBJETO A UPPERCASE
	Elemento.value = Elemento.value.toUpperCase();
}


/******************************************************************************
DESCRIPCION: ASIGNA EL VALOR "" A LOS ELEMENTOS INPUT="TEXT" , TEXTAREA,
SELECT, HIDDEN DE LOS FILTROS DE B�SQUEDA 
AUTOR: JAVIER SANCHEZ
FECHA DE CREACI�N 		25/NOV/2003
PARAMETROS: SIN PARAMETROS
******************************************************************************/		

function limpia(){	 
	
	// ARREGLO QUE GUARDAR� LOS NOMBRES DE LOS OBJETOS QUE TIENEN ID E INNERTEXT
	// PARA DESPU�S BORRARLOS, ESTOS SON LOS QUE SE DEVUELVEN A LA PANTALLA DE LA 
	// FUNCI�N REGRESADATOS, LOS DEM�S CAMPOS HIDDEN NO SE MODIFICAR�N
	arregloCamposConId = new Array();
	iContador = 0;
	
	// ITERA LAS CELDAS DE LA TABLA PARA BORRAR LOS QUE TIENEN ID
	for (f = 0; f < TablaBusqueda.rows.length; f++){
		for (c = 0; c < TablaBusqueda.rows(f).cells.length; c++){
			tablaCell = TablaBusqueda.rows(f).cells(c);
			if(tablaCell.id){
				if(tablaCell.innerText){
					tablaCell.innerText = '';
					arregloCamposConId[iContador] = tablaCell.id;
					iContador++;
				}
			}
		}

	}
	
	// VERIFICAMOS SI SE TRATA DE INTERNET EXPLORER
	if (document.all){
		// SE OBTIENE EL N�MERO TOTAL DE ELEMENTOS
		totalElementos = document.frmRegistro.elements.length;
		// SE ITERAN 
		for(i = 0; i < totalElementos; i++){
			elemento = document.frmRegistro.elements[i];
			//alert(elemento.type);
			//alert(elemento.name);
			// SI SON BOTONES O SUBMIT NO LOS MODIFICA 
			if((elemento.type == "submit") || (elemento.type == "button")){
				return;
			}
			else{
				// VALIDA SI ES HIDDEN  
				if(elemento.type == "hidden"){
					for(s = 0; s < iContador; s++){
						// SI ESTA CONTENIDO EN EL ARREGLO SE BORRA
						if( elemento.name == arregloCamposConId[s]){
							elemento.value = '';
						}
					}
				}
				else{
					// POR EL MOMENTO NO SE REQUIERE BORRAR SELECTS
					// SI ES TIPO SELECT
				  	if(elemento.type == "select-one"){
						BuscaSelectOpcion(elemento,'null');
					}
					else{
						if(elemento.type == "radio" || elemento.type == "checkbox"){
							elemento.checked = false;
						}
						else{
							// SI ES CUALQUIER OTRO TIPO
							elemento.value = "";
							//alert('modifica no hidden : ' + elemento.name);
						}
					}
				}
			}
		}
	}
}
	


/******************************************************************************
DESCRIPCION: SECCION DE VALIDACIONES PARA ALTAS DE DOMICILIOS
AUTOR: CARLOS PEREZ
FECHA DE CREACI�N 		17/ENE/2009
PARAMETROS: SIN PARAMETROS
******************************************************************************/		
 
	function validaFiscal(){
		

		var chkFiscal =document.domiciliosForm.chkFiscal;
		var fiscal = document.domiciliosForm.fiscal;
		
		if(chkFiscal.checked == true){
			
			fiscal.value = "V";
			
		}else{
			
			fiscal.value = "F";
		}

	}


	function buscaEstado(){
		
		validaFiscal()
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.Metodo.value = "Estado"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		formulario.submit();
	
	}	
	
	function buscaColonias(){
		
		 validaFiscal()
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.Metodo.value = "Municipio"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		formulario.submit();
	
	}	
	
	
	function buscaLocalidades(){
		
		 validaFiscal()
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.Metodo.value = "Colonia"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		formulario.submit();
	
	}	
	
	function buscaCP(){
		
		var colonia = document.domiciliosForm.colonia

		if(colonia.value == "-1"){
			
			muestraMensajes ("Se debe seleccionar una colonia valida", "tache.png");
			return;
			
		}
		
		 validaFiscal()
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.Metodo.value = "Colonia"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		formulario.submit();
	
	}	
	//javascript:buscaCPLocalidad();
	
	
	function buscaCPLocalidad(){
		
		
		var localidad = document.domiciliosForm.localidad

		if(localidad.value == ""){
			
			muestraMensajes ("Se debe seleccionar una localidad valida", "tache.png");
			return;
			
		}
		
		 validaFiscal()
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.Metodo.value = "Localidad"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		formulario.submit();
	
	}	
	
	function cancelar(){
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.Metodo.value = "Cancelar"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		formulario.submit();
	
	}	
	
	function buscaTodos(){
		validaFiscal();
		
		
		
		var formulario = document.getElementsByName("domiciliosForm")[0];

		var cp = document.domiciliosForm.cp.value;
		
		document.domiciliosForm.Metodo.value = "CP"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "IN"
		
		if(cp.length == 5){
			
			formulario.submit();
			
		}
		
		
		
		
	}	
	
	function combos(cp, localidad, calle, numeroExterior, numeroInterior, entreCalle, entreCalle2,  puntoReferencia,
	
								callePosterior,  caracteristicasDomicilio, idOperacion, idDomicilio , tipoDomicilio, fiscal){
		
		document.domiciliosForm.idOperacion.value = idOperacion;

		

		var agregar = document.getElementById("agregar");
		var modificar = document.getElementById("modificar");
		var enviarI  = document.getElementById("enviarI");
		var enviarU  = document.getElementById("enviarU");
		var inicio = document.getElementById("inicio");
		var chkFiscal =document.domiciliosForm.chkFiscal;
	
		
		if(fiscal == "V"){
			
			chkFiscal.checked=true;
		}
		
		var tipoOperacion;
		
		if(idDomicilio != null && idDomicilio  != ""){
			
			tipoOperacion = 'U';
			

		}else{
			
			tipoOperacion = 'I';
						
			if(tipoDomicilio == "26"){
				document.domiciliosForm.tipoDomicilio.value=tipoDomicilio;
			}			
			
		}
		
		
		
		enviarI.href="javascript:enviar('"+tipoOperacion+"')";
		
		
		
		if(idDomicilio != null){
		document.domiciliosForm.idDomicilio.value = idDomicilio;
		}
	
		if(cp != null){
		document.domiciliosForm.cp.value = cp;
		}
		
		if(localidad != null){
		document.domiciliosForm.localidad.value = localidad;
		}
		
		if(calle != null){
		document.domiciliosForm.calle.value =calle;
		}	
		
		if(numeroExterior != null){
		document.domiciliosForm.numeroExterior.value = numeroExterior;
		}
		if(numeroInterior != null){
		document.domiciliosForm.numeroInterior.value = numeroInterior;
		}
		if(entreCalle != null){
		document.domiciliosForm.entreCalle.value = entreCalle;
		}
		if(entreCalle2 != null){
		document.domiciliosForm.entreCalle2.value = entreCalle2;
		}
		if(puntoReferencia != null){
		document.domiciliosForm.puntoReferencia.value = puntoReferencia;
		}
		
		if(callePosterior != null){
		document.domiciliosForm.callePosterior.value = callePosterior;
		}
		if(caracteristicasDomicilio != null){
		document.domiciliosForm.caracteristicasDomicilio.value = caracteristicasDomicilio;
		}
		
		
	}
	
	function _validaDomicilios(){
		
		var calle = document.domiciliosForm.calle.value;
		var tipoVilaidad = document.domiciliosForm.tipoVialidad.value;	
		var numeroExterior = document.domiciliosForm.numeroExterior.value;
		var cp = document.domiciliosForm.cp.value;
		var estado = document.domiciliosForm.estado.value;
		var delegacion = document.domiciliosForm.delegacion.value;
		var colonia = document.domiciliosForm.colonia.value;
		var localidad = document.domiciliosForm.localidad.value;		
		var tipoInmueble = document.domiciliosForm.tipoInmueble.value;
		var entreCalle = document.domiciliosForm.entreCalle.value;
		var entreCalle2 = document.domiciliosForm.entreCalle2.value;
		var callePosterior = document.domiciliosForm.callePosterior.value;
		var puntoReferencia = document.domiciliosForm.puntoReferencia.value;
		
		if(tipoVilaidad==""){
			muestraMensaje ("Selccione un tipo de vialidad ", "tache.png");
			document.domiciliosForm.tipoVialidad.focus();
			return false;
		}
		if(calle==""){
			muestraMensaje ("Indique la calle del domicilio ", "tache.png");
			document.domiciliosForm.calle.focus();
			return false;
		}
		if(numeroExterior==""){
			muestraMensaje ("Indique el número exterior del domicilio, si no cuenta con ello coloque s/n ", "tache.png");
			document.domiciliosForm.numeroExterior.focus();
			return false;
		}		
		if((cp=="") || (cp.length<5)){
			muestraMensaje ("Ingrese un código postal válido ", "tache.png");
			document.domiciliosForm.cp.focus();
			return false;
		}
		if(estado==""){
			muestraMensaje ("Seleccione un Estado ", "tache.png");
			document.domiciliosForm.estado.focus();
			return false;
		}
		if(delegacion==""){
			muestraMensaje ("Seleccione una delegación ó municipio ", "tache.png");
			document.domiciliosForm.delegacion.focus();
			return false;
		}
		if(colonia=="" && localidad ==""){
			muestraMensaje ("Seleccione una colonia ó localidad ", "tache.png");
			document.domiciliosForm.colonia.focus();
			return false;
		}
		if(tipoInmueble==""){
			muestraMensaje ("Seleccione el tipo de inmueble ", "tache.png");
			document.domiciliosForm.tipoInmueble.focus();
			return false;
		}
		if(entreCalle==""){
			muestraMensaje ("Indique entre que calle se ubica", "tache.png");
			document.domiciliosForm.entreCalle.focus();
			return false;
		}
		if(entreCalle2==""){
			muestraMensaje ("Indique entre que calle se ubica", "tache.png");
			document.domiciliosForm.entreCalle2.focus();
			return false;
		}
		if(callePosterior==""){
			muestraMensaje ("Indique la calle posterior", "tache.png");
			document.domiciliosForm.callePosterior.focus();
			return false;
		}
		if(puntoReferencia==""){
			muestraMensaje ("Indique un punto de referencia", "tache.png");
			document.domiciliosForm.puntoReferencia.focus();
			return false;
		}
		
		return true;
	}
	
	function enviar(operacion, forward){
		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.forward.value = forward;
		
		
		var idOperacion = document.domiciliosForm.idOperacion.value;
		
		if(idOperacion == null || idOperacion == ""){
			document.domiciliosForm.idOperacion.value = "I";
		}
	
		validaFiscal();
		
		if(_validaDomicilios()){
			document.domiciliosForm.action = "/portal/PortalController";
			document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
			document.domiciliosForm.OperacionCatalogo.value = "AL"
			formulario.submit();
		}

	}
	
	function subTexto(texto){
	
	return texto.substring(1,0);
	
	
	
	
	}
	
	
	function consultaDomicilio(idDomicilio){


		var formulario = document.getElementsByName("domiciliosForm")[0];

		document.domiciliosForm.action = "/portal/PortalController";
		document.domiciliosForm.Metodo.value = "Consulta"
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.idDomicilio.value = idDomicilio;
		document.domiciliosForm.OperacionCatalogo.value = "IN";
		
		formulario.submit();
		
	}
	
	
	function borrarDomicilio(idDomicilio, tipoDomicilio){


		var formulario = document.getElementsByName("domiciliosForm")[0];


		
		document.domiciliosForm.Funcion.value = "CiudadanoDomiciliosNacionales"
		document.domiciliosForm.OperacionCatalogo.value = "AL"
		document.domiciliosForm.idDomicilio.value = idDomicilio;
		document.domiciliosForm.idTipoDomicilio.value = tipoDomicilio;
		document.domiciliosForm.idOperacion.value = "D";
		formulario.submit();
		
	}

	function habilita_tabla()
    {
		
		var div_tabla_domicilios = document.getElementById("div_tabla_domicilios");
		div_tabla_domicilios.style.display='block';
    }
	
    function deshabilita_tabla()
    {
    	try {
    		var div_tabla_domicilios = document.getElementById("div_tabla_domicilios");
    		div_tabla_domicilios.style.display='none';
    	} catch(err) {
//    		alert(err.description );
    	}
    }   
    
    
    
    
    function defineTabla()
    {
    	document.domiciliosForm.registroTabla.value = "1";
    }

	
	
	function AnteriorPestana(idControlUnico){
        MM_goToURL('parent','/portal/solicitudApertura/empresa/actividades/actividadesBegin.do');
		}
	
	function goSolicitudes(){
       MM_goToURL('parent','/portal/PortalController?Servicio=ProcesosControlSolicitudes&OperacionCatalogo=CT&Filtro=Todos');
    }

	function SiguientePestana(){
       MM_goToURL('parent','/portal/PortalController?Servicio=ProcesosSocAdmCapitalSocial&OperacionCatalogo=IN&Filtro=Inicio');
    }
	
	
	
	
	