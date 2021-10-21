<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="mx.gob.se.rug.seguridad.to.PrivilegioTO"%>
<%@page import="mx.gob.se.rug.seguridad.dao.PrivilegiosDAO"%>
<%@page import="mx.gob.se.rug.seguridad.to.PrivilegiosTO"%>
<%@page import="mx.gob.se.rug.to.UsuarioTO"%>
<%@page import="mx.gob.se.rug.seguridad.to.MenuTO"%>
<%@page import="mx.gob.se.rug.seguridad.serviceimpl.MenusServiceImpl"%>
<%@page import="mx.gob.se.rug.constants.Constants"%>
<%@include file="/WEB-INF/jsp/Layout/menu/applicationCtx.jsp" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<main>
<%
//Privilegios
PrivilegiosDAO privilegiosDAO = new PrivilegiosDAO();
PrivilegiosTO privilegiosTO = new PrivilegiosTO();
privilegiosTO.setIdRecurso(new Integer(6));
privilegiosTO=privilegiosDAO.getPrivilegios(privilegiosTO,(UsuarioTO)session.getAttribute(Constants.USUARIO));
Map<Integer,PrivilegioTO> priv= privilegiosTO.getMapPrivilegio();

%>
<input type="hidden" name="tipoBienAll" value="false" id="idTipoBienAll" />
<div class="container-fluid">
	<div class="row">
			<div id="menuh">
			<ul>
				<%
				UsuarioTO usuarioTO = (UsuarioTO)session.getAttribute(Constants.USUARIO);
				MenuTO menuTO= new MenuTO(1,request.getContextPath());	
				MenusServiceImpl menuService = (MenusServiceImpl)ctx.getBean("menusServiceImpl");
				
				Boolean noHayCancel= (Boolean) request.getAttribute("noHayCancel");
				Boolean noVigencia = (Boolean) request.getAttribute("vigenciaValida");
				if(noHayCancel==null ||(noHayCancel!=null && noHayCancel.booleanValue()==true)){
					Integer idAcreedorRepresentado=(Integer) session.getAttribute(Constants.ID_ACREEDOR_REPRESENTADO);
					MenuTO menuSecundarioTO = new MenuTO(2, request.getContextPath());
					menuSecundarioTO = menuService.cargaMenuSecundario(idAcreedorRepresentado,usuarioTO,menuSecundarioTO,noVigencia);
					Iterator<String> iterator2 = menuSecundarioTO.getHtml().iterator();
					while (iterator2.hasNext()) {
						String menuItem = iterator2.next();
				%><%=menuItem%>
				<%
					}
				}
					
				%>
			</ul>
		</div>
	</div>
	<div id="modifica" class="row">
		<div class="col s12"><div class="card">
			<div class="col s2"></div>
			<div class="col s8">
				<form id="famodificacion" name="famodificacion" action="savemodificacion.do" method="post">
					<span class="card-title">Modificaci&oacute;n de la Garant&iacute;a Mobiliaria</span>
					<input type="hidden" name="refInscripcion" id="refInscripcion" value="<s:property value='idTramite'/>" />
					<div class="row note">
						<p>
							<span>Mediante esta operaci&oacute;n usted podr&aacute; modificar: </span><br>
							<span>1) Sustituci&oacute;n de la Garant&iacute;a Mobiliaria inscrita en el RGM; </span><br>
							<span>2) Amplicaci&oacute;n de la Garant&iacute;a Mobiliaria inscrita en el RGM; y o </span><br>
							<span>3) Los Deudores Garantes </span>
							<span>4) Los Acreedores Garantizados </span>
						</p>
					</div>
					<div class="row note teal">
						<span class="white-text">Datos de la Inscripci&oacute;n</span>
					</div>
					<div class="row">
						<div class="input-field col s12">							
							<s:textfield name="vigencia" id="vigencia" value="%{vigencia}" disabled="true"/>
							<label for="vigencia">Vigencia: </label>
						</div>
					</div>
					<div class="row">
						<span class="blue-text text-darken-2"><s:property value="%{textosFormulario.get(1)}"/></span>
						<div id ="divParteDWRxx2"></div>
					</div>
					<s:if test="hayAcreedores">
						<div class="row">							
							<span class="blue-text text-darken-2"><s:property value="%{textosFormulario.get(2)}"/></span>
							<div id="divParteDWRxx3"></div>							
						</div>
					</s:if>
					<s:if test="hayOtorgantes">
						<div class="row">							
							<span class="blue-text text-darken-2"><s:property value="%{textosFormulario.get(3)}"/></span>
							<div id="divParteDWRxx4"></div>							
						</div>
					</s:if>
				 	<div class="row">
				    	<div class="input-field col s12">
				    		<s:textarea rows="10" cols="80" name="modotroscontrato"
	        				id="modotroscontrato" value="%{modotroscontrato}"
						    maxlength="3000" onkeyup="return ismaxlength(this)" />
	        				<label for="modotroscontrato">Datos del Representante(s)</label>
				   		</div>
				 	</div>
					<div class="row note teal">
						<span class="white-text">Informaci&oacute;n de la Garant&iacute;a
							Mobiliaria</span>
					</div>															
					<div class="row">
						<div class="input-field col s12">
							<s:textarea name="moddescripcion" cols="70" rows="10" id="tiposbienes" value="%{moddescripcion}" />
							<label for="tiposbienes"><s:property value="%{textosFormulario.get(4)}"/></label>							
						</div>
					</div>	
					<div class="row">
						<span class="blue-text text-darken-2">Bienes en garant&iacute;a si estos tienen n&uacute;mero de serie:</span>
					</div>
					<div class="row">		
						<div class="col s12 right-align">
							<a class="btn-floating btn-large waves-effect indigo modal-trigger" onclick="limpiaCampos()"
								href="#frmBien" id="btnAgregar"><i
								class="material-icons left">add</i></a>
						</div>																
						<div id="divParteDWRBienes"></div>						
					</div>
					<div class="row note teal">
						<span class="white-text">Informaci&oacute;n Espec&iacute;fica de la Garant&iacute;a Mobiliaria</span>
					</div>
					<div class="row">
				    	<p>
				        	<input type="checkbox" name="aBoolean" id="aBoolean" value="true"/>							        	
				        	<label for="aBoolean">Declaro que de conformidad con el contrato de garant&iacute;a, el deudor declar&oacute; que sobre los bienes en garant&iacute;a no existen otro gravamen, anotaci&oacute;n o limitaci&oacute;n previa.</label>
				   		</p>
				 	</div>
				 	<div class="row">
				    	<p>
				        	<input type="checkbox" name="aMonto" id="aMonto" value="true"/>
				        	<label for="aMonto">Los atribuibles y derivados no esta afectos a la Garant&iacute;a Mobiliaria</label>
				   		</p>
				 	</div>
				 	<div class="row">
				    	<p>
				        	<input type="checkbox" name="aPrioridad" id="aPrioridad" value="true" onclick="esPrioritaria()"/>
				        	<label for="aPrioridad">Es prioritaria la garant&iacute;a mobiliaria</label>
				   		</p>
				 	</div>
				 	<div class="row">							    	
			    		<p>							    		
			    			<input type="checkbox" name="aRegistro" id="aRegistro" value="true" onclick="otroRegistro()"/>
			        		<label for="aRegistro">El bien se encuentra en otro registro</label>
			        	</p>
			        </div>
			        <div class="row">						   		
				   		<div class="input-field col s6">
				    		<s:textarea name="txtregistro" id="txtregistro" value="%{txtregistro}" disabled="true"/>						    	 								        	
				        	<label for="txtregistro">Especifique cual</label>
				   		</div>
				 	</div>															 
				 	<div class="row">
				    	<div class="input-field col s12">
				        	<s:textarea rows="10" cols="80" id="instrumento" name="instrumento" value="%{instrumento}" 
									    maxlength="3000" />
				        	<label for="instrumento"><s:property value="%{textosFormulario.get(7)}"/></label>
				   		</div>
				 	</div>				 		
				 	<div class="row">
				    	<div class="input-field col s12">
				    		<s:textarea id="modotrosgarantia" name="modotrosgarantia" cols="80" rows="10" value="%{modotrosgarantia}" maxlength="3500"/>				        										
				        	<label for="modotrosgarantia"><s:property value="%{textosFormulario.get(8)}"/></label>
				   		</div>
				 	</div>	
                                        <s:if test="detalleTO.idtipogarantia==16">
                                            <div class="row">
                                                <div class="input-field col s12">
                                                    <s:select  name="idTipoMoneda" value="%{detalleTO.idMoneda}" list="listaMonedas" listKey="idMoneda" listValue="descMoneda"
                                                                id="idTipoMoneda" />
                                                        <label for="idTipoMoneda">Seleccione la moneda:</label>
                                                </div>
                                                <div class="input-fieldx col s12">
                                                    <label for="monto_calculo">Monto estimado de la garantia</label>
                                                    <s:textfield   name="modmonto" value="%{detalleTO.monto}" id="modmonto" min="0" onchange="limpiar_monto()"/>


                                                        <s:textfield  name="actoContratoTO.montoMaximo" class="monto_maximo" type="hidden" min="0" 
                                                                id="actoContratoTO.montoMaximo"  />

                                                </div>
                                            </div>

                                        </s:if>
				 	<div class="row note teal">							    	
			        	<span class="white-text">
			        		Objeto de la Modificaci&oacute;n
						</span>							   		
				 	</div>
				 	<div class="row">
				    	<div class="input-field col s12">
				    		<s:textarea id="modotrosterminos" name="modotrosterminos" cols="80" rows="10" maxlength="3500"/>				        										
				        	<label for="modotrosterminos">Observaciones a la Modificaci&oacute;n:</label>
				   		</div>
				 	</div>				 	
				    <hr />
				 	<center>
			            <div class='row'>			            	
			            	<input type="button" id="bFirmar" name="button" class="btn btn-large waves-effect indigo" value="Aceptar" onclick="validarSinAutoridad();"/>			            				            							            	
			            </div>
		          	</center>						
				</form>	
				<s:if test="aBoolean">
					<script type="text/javascript">
							document.getElementById('aBoolean').checked = 1;
					</script>
				</s:if>		
				<s:if test="aMonto">
					<script type="text/javascript">
							document.getElementById('aMonto').checked = 1;
					</script>
				</s:if>
				<s:if test="aPrioridad">
					<script type="text/javascript">
							document.getElementById('aPrioridad').checked = 1;
					</script>
				</s:if>
				<s:if test="aRegistro">
					<script type="text/javascript">
							document.getElementById('aRegistro').checked = 1;
							document.getElementById("txtregistro").disabled = false;
							Materialize.updateTextFields();
					</script>
				</s:if>						
			</div>
		</div></div>	
	</div>
</div>
<script type="text/javascript"> 	
					var idPersona = <s:property value="idpersona"/>;
					var idTramite= <s:property value="idTramite"/>;
		
					cargaParteDeudor('divParteDWRxx2',idTramite, idPersona,'0','2');
					cargaParteAcreedor('divParteDWRxx3',idTramite, idPersona,'0','2');
					cargaParteOtorgante('divParteDWRxx4',idTramite, idPersona,'0','2');
					cargaParteBienes('divParteDWRBienes',idTramite);

					//escondePartes();	
				</script> 
</main>
<div id="frmBien" class="modal">
	<div class="modal-content">
		<div class="card">
			<div class="card-content">						
				<span class="card-title">Bien Especial</span>
				<div class="row">
					<div class="col s1"></div>
					<div class="col s10">
						<div class="row">
							<div class="input-field col s12">
								<s:select name="mdBienEspecial" id="mdBienEspecial" list="listaBienEspecial" listKey="idTipo" listValue="desTipo" 
									 onchange="cambiaBienesEspeciales()"/>								    
						    	<label>Tipo Bien Especial</label>
						  	</div>
						</div>
						<div id="secId4" class="row">
							<div class="input-field col s6">
								<s:textfield name="mdFactura1" id="mdFactura1"
									cssClass="validate" maxlength="150" />
								<label id="lblMdFactura1" for="mdFactura1">Emitido por:</label>
							</div>						
							<div class="input-field col s6">
								<input type="text" name="mdFactura2" class="datepicker" id="mdFactura2" />									
								<label id="lblMdFactura2" for="mdFactura2">Fecha: </label>
							</div>						
						</div>
						<div class="row">
							<div class="input-field col s12">
								<s:textarea rows="10" id="mdDescripcion" cols="80" 												
											name="mdDescripcion" />							    	 								        	
				        		<label id="lblMdDescripcion" for="mdDescripcion">Descripci&oacute;n del bien</label>
				        	</div>
						</div>							
						<div id="secId1"class="row" style="display: none;">
							<div class="input-field col s3">
								<label id="lblMdIdentificador">Placa</label>
							</div>
							<div class="input-field col s3">
								<s:select name="mdIdentificador" id="mdIdentificador" list="listaUsos" listKey="idTipo" listValue="desTipo"/>								
							</div>
							<div class="input-field col s6">
								<s:textfield name="mdIdentificador1" id="mdIdentificador1"
									cssClass="validate" maxlength="150" />								
							</div>
						</div>
						<div id="secId2" class="row" style="display: none;"><span class="col s12 center-align">Y</span></div>
						<div id="secId3" class="row" style="display: none;">
							<div class="input-field col s12">
								<s:textfield name="mdIdentificador2" id="mdIdentificador2"
									cssClass="validate" maxlength="150" />
								<label id="lblMdIdentificador2" for="mdIdentificador2">VIN 2</label>
							</div>
						</div>														
						<br />
						<hr />
						<center>
							<div id="secId5" class="row">
								<a href="#!"
									class="btn-large indigo"
									onclick="add_bien();">Aceptar</a>
							</div>
							<div id="secId6" class="row">
								<a href="#!" id="formBienButton"
									class="btn-large indigo"
									onclick="add_bien();">Aceptar</a>
							</div>
						</center>
					</div>
					<div class="col s1"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/material-dialog.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"> 
function add_bien() {
	  
	  var idTramite = document.getElementById("refInscripcion").value;
	  var mdDescripcion = document.getElementById("mdDescripcion").value;
	  var idTipo = document.getElementById("mdBienEspecial").value;
	  var mdIdentificador = document.getElementById("mdIdentificador").value;
	  var mdIdentificador1 = document.getElementById("mdIdentificador1").value;
	  var mdIdentificador2 = document.getElementById("mdIdentificador2").value;
	  
          console.log("Data " , idTramite, " ", mdDescripcion, " ", idTipo, " ", mdIdentificador, " ", mdIdentificador1, " ", mdIdentificador2)
          
	  if(!noVacio(mdDescripcion)){
		  alertMaterialize('Debe ingresar la descripcion del bien especial');
		  return false;
	  }
	  
	  if(idTipo == '2'){
		  mdDescripcion = 'Emitido por: ' + document.getElementById("mdFactura1").value + " Fecha: " + document.getElementById("mdFactura2").value + " " + mdDescripcion;
	  }
	  
	  ParteDwrAction.registrarBien('divParteDWRBienes',idTramite, mdDescripcion, idTipo, mdIdentificador, 
			  mdIdentificador1, mdIdentificador2, showParteBienes);
	  
	  $(document).ready(function() {	  
			$('#frmBien').modal('close');
		});
}

function cambiaBienesEspeciales() {
	  var x = document.getElementById("mdBienEspecial").value;
	  
	  if(x=='1'){
		  document.getElementById("mdDescripcion").disabled = false;	  
		  document.getElementById("secId1").style.display = 'block'; 
		  document.getElementById("secId2").style.display = 'block';
		  document.getElementById("secId3").style.display = 'block';
		  document.getElementById("secId4").style.display = 'none';
		  
		  document.getElementById("lblMdDescripcion").innerHTML = 'Descripci&oacute;n del veh&iacute;culo';
		  document.getElementById("lblMdIdentificador2").innerHTML = 'VIN';
	  } else if (x=='2'){
		  document.getElementById("mdDescripcion").disabled = false;	  
		  document.getElementById("secId1").style.display = 'none'; 
		  document.getElementById("secId2").style.display = 'none';
		  document.getElementById("secId3").style.display = 'block';		
		  document.getElementById("secId4").style.display = 'block';
		  
		  document.getElementById("lblMdIdentificador2").innerHTML = 'No. Factura';
		  document.getElementById("lblMdDescripcion").innerHTML = 'Observaciones Generales';
	  } else if (x=='3'){
		  document.getElementById("mdDescripcion").disabled = false;	  
		  document.getElementById("secId1").style.display = 'none'; 
		  document.getElementById("secId2").style.display = 'none';
		  document.getElementById("secId3").style.display = 'block';		
		  document.getElementById("secId4").style.display = 'none';
		  
		  document.getElementById("lblMdIdentificador2").innerHTML = 'No. Serie';
		  document.getElementById("lblMdDescripcion").innerHTML = 'Descripci&oacute;n del bien';
	  }
	  
	  
}
  
function limpiaCampos() {
	  document.getElementById("secId1").style.display = 'none'; 
	  document.getElementById("secId2").style.display = 'none';
	  document.getElementById("secId3").style.display = 'none';
	  document.getElementById("secId4").style.display = 'none';
	  document.getElementById("secId5").style.display = 'block';
	  document.getElementById("secId6").style.display = 'none';
	  
	  document.getElementById("mdIdentificador").value = '0';
	  document.getElementById("mdIdentificador1").value = '';
	  document.getElementById("mdIdentificador2").value = '';
	  
	  document.getElementById("mdFactura1").value = '';
	  document.getElementById("mdFactura2").value = '';
	  	  
	  document.getElementById("mdDescripcion").value = '';	  
	  document.getElementById("mdDescripcion").disabled = true;	  
	  
	  document.getElementById("mdBienEspecial").value  = '0';
	  
	  
	  Materialize.updateTextFields();
}
  
  function esPrioritaria() {
	  var checkBox = document.getElementById("actoContratoTO.garantiaPrioritaria");
	  if (checkBox.checked == true) {
		  MaterialDialog.alert(
					'<p style="text-align: justify; text-justify: inter-word;">Recuerde: <b>Artï¿½culo 17. Garantia Mobiliria Prioritaria.</b> ' +
					'La publicidad de la garantía mobiliaria se constituye por medio de la inscripciï¿½n del formulario registral, '+
					'que haga referencia al carï¿½cter prioritario especial de esta garantï¿½a y que describa los bienes gravadoas por '+
					'categorï¿½a, sin necesidad de descripciï¿½n pormenorizada. <br> <br>' +
					'Para el caso se consituya respecto de bienes que '+
		    	    'pasarï¿½n a formar parte del inventario el deudor garante, el acreedor garantizado que financie adquisicion ' +
		    	    'de la garantï¿½a mobiliaria prioritaria deberï¿½ notificar por escrito, en papel o por medio de un documento ' +
		    	    'electrï¿½nico, con anterioridad o al momento de la inscripciï¿½n e esta garantï¿½a, a aquello acreedores garantizados '+
		    	    'que hayan inscrito previamente garantï¿½as mobiliarias sobre el inventario, a fin de que obtenga un grado de ' +
		    	    'prelacion superior al de estos acreedores. <br><br>'+
		    	    '<b>Artï¿½culo 56. Prelaciï¿½n de la garantï¿½a mobiliaria prioritaria para la adquisiciï¿½n de bienes. </b> Una garantï¿½a ' +
		    	    'mobiliaria prioritaria para la adquisiciï¿½n de bienes especï¿½ficos tendrï¿½ prelaciï¿½n sobre cualquier '+
					'garantï¿½a anterior que afecte bienes muebles futuros del mismo tipo en '+
					'posesiï¿½n del deudor garante, siempre y cuando la garantï¿½a mobiliaria '+
					'prioritaria se constituya y publicite conforme lo establecido por esta '+
					'ley, aï¿½n y cuando a esta garantï¿½a mobiliaria prioritaria se le haya dado '+
					'publicidad con posterioridad a la publicidad de la garantï¿½a anterior. La '+
					'garantï¿½a mobiliaria prioritaria para la adquisiciï¿½n de bienes especï¿½ficos '+
					'se extenderï¿½ exclusivamente sobre los bienes muebles especï¿½ficos '+
					'adquiridos y al numerario especï¿½ficamente atribuible a la venta de estos '+
					'ï¿½ltimos, siempre y cuando el acreedor garantizado cumpla con los '+
					'requisitos de inscripciï¿½n de la garantï¿½a mobiliaria prioritaria, '+
					'establecidos en esta ley. </p>',
					{
						title:'Garantia Prioritaria', // Modal title
						buttons:{ // Receive buttons (Alert only use close buttons)
							close:{
								text:'close', //Text of close button
								className:'red', // Class of the close button
								callback:function(){ // Function for modal click
									//alert("hello")
								}
							}
						}
					}
				);		     
		  } 
  }
  
  function otroRegistro() {
	  var checkBox = document.getElementById("aRegistro");
	  if (checkBox.checked == true) {
		  document.getElementById("txtregistro").disabled = false;
		  Materialize.updateTextFields();
	  } else {
		  document.getElementById("txtregistro").value  = '';
		  document.getElementById("txtregistro").disabled = true;
		  Materialize.updateTextFields();
	  }
  }
  
</script>
