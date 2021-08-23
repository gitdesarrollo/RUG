<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%">
	<tr>
		<td class="encabezadoTablaResumen" width="100%">
			<s:text name="Activacion de cuenta" />
		</td>
	</tr>
</table>

<s:actionmessage />
<s:actionerror />

<s:form namespace="usuario" action="activAcreedor.do" theme="simple">
<INPUT type="hidden" name="personaFisica.datosContacto.emailPersonal" value="<s:property value="personaFisica.datosContacto.emailPersonal"/>" id="personaFisica.datosContacto.emailPersonal">
<INPUT type="hidden" name="personaFisica.nombre" value="<s:property value="personaFisica.nombre"/>" id="personaFisica.nombre">
<INPUT type="hidden" name="personaFisica.apellidoPaterno" value="<s:property value="personaFisica.apellidoPaterno"/>" id="personaFisica.apellidoPaterno">
<INPUT type="hidden" name="personaFisica.apellidoMaterno" value="<s:property value="personaFisica.apellidoMaterno"/>" id="personaFisica.apellidoMaterno">
	<table class="formularioSide">
		<tr>
			<td width="85%">
				<table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td class="textoGeneralAzul" valign="bottom" align="right"><b class="textoGeneralRojo"></b><s:text name="Usuario :" /></td>
						<td class="campo" valign="bottom">
							<s:property value="personaFisica.nombre"/> <s:property value="personaFisica.apellidoPaterno"/> <s:property value="personaFisica.apellidoMaterno"/>
						</td>
					</tr>
					
					<tr>
						<td class="textoGeneralAzul" valign="bottom" align="right"><b class="textoGeneralRojo"></b><s:text name="Clave de usuario :" /></td>
						<td class="campo" valign="bottom">
							<s:property value="personaFisica.datosContacto.emailPersonal"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="3">
							<div id=scrolltable style=" background: #FFFFFF; overflow:auto;
							border-right: #6699CC 1px solid; border-top: #999999 1px solid;
							border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
							scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
							scrollbar-track-color :#3333333 ; 
							height:200px; left: 100; top: 20; width: 80%">
							
							<table cellpadding="3" cellspacing="2" width="100%">
							<tr>
									<td class="texto_general" colspan="2" align="justify" >
											<s:text name="usuario.terminos.politicas"/>	
									</td>
							</tr>										
							</table>		
							<table cellpadding="3" cellspacing="2" width="100%">
						<tr>
							<td align="center" class="tituloInteriorRojo"><br>
							<b><s:text name="publico.ayuda.terminos.de.uso.titulo" /></b></td>
						</tr>
					</table>

					<table cellpadding="3" cellspacing="2" width="100%" border="0">
						<tr>
							<td align="justify"><br class="texto_general">
							<s:text name=" " /></td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Estimado usuario, esta p�gina establece los" /> "<s:text name="T�rminos y Condiciones" />" <s:text name="que regulan el uso del Registro �nico de Garant�as Mobiliarias, en adelante el RUG, de conformidad con lo dispuesto por el art�culo 32 bis 2 y siguientes del C�digo de Comercio, como una secci�n del Registro P�blico de Comercio, en el cual se pueden inscribir todo tipo de garant�as mobiliarias, privilegios especiales y derechos de retenci�n sobre bienes muebles para garantizar cualesquiera cr�ditos que una persona reciba y que funciona a trav�s del sitio de Internetwww.rug.gob.mx." />
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="La persona que haga uso del sitio de Internet arriba citado, en adelante" /> "<s:text name="el usuario" />" <s:text name=", debe aceptar los presentes T�rminos y Condiciones, por lo que est� obligada a leerlos y analizarlos detenidamente antes de continuar, ya que por el simple uso o acceso a cualquiera de las p�ginas que integran el RUG se entender� que acepta y est� de acuerdo en obligarse a cumplir con estos T�rminos y Condiciones. En caso de no estar de acuerdo en sujetarse a los mismos, debe suspender inmediatamente su uso y abstenerse de acceder al RUG, as� como a la informaci�n, contenidos o programas que forman parte del mismo. Estos T�rminos y Condiciones podr�n ser modificados libremente por la Secretar�a de Econom�a, por lo que se recomienda ampliamente al usuario realizar una consulta peri�dica de los mismos para conocer las disposiciones vigentes al momento de acceder a la p�gina." />
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="El usuario tendr� acceso al RUG a trav�s de su dominio de Internetwww.rug.gob.mxy a trav�s de sus funcionalidades y contenidos podr� realizar diversos tr�mites registrales puestos a su disposici�n por la Secretar�a de Econom�a. El usuario acepta ser el �nico responsable de la informaci�n enviada al RUG a trav�s del sistema, as� como de la existencia y veracidad de la documentaci�n relacionada con el acto jur�dico que se inscriba y de la identidad de las personas que lo celebran." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Firma electr�nica." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="En los t�rminos del Reglamento del Registro P�blico de Comercio es necesario que el usuario firme electr�nicamente las operaciones que realiza en el RUG  en las que ingresa informaci�n a la base de datos nacional del RUG, entendi�ndose satisfecho dicho requisito al utilizar las firmas electr�nicas avanzadas autorizadas o reconocidas por la Secretar�a de Econom�a." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Al momento de firmar electr�nicamente cualquier operaci�n en este sitio de Internet, el firmante acepta conocer y se adhiere plenamente y sin reservas a los presentes T�rminos y Condiciones, as� como el contenido, la existencia y veracidad de la informaci�n enviada y documentaci�n relativa a la operaci�n que se lleva a cabo. Los actos y/o documentos inscritos en el RUG con el uso de firma electr�nica avanzada, producir�n los mismos efectos que las leyes otorgan a los documentos firmados aut�grafamente. La firma electr�nica avanzada utilizada para firmar lainformaci�n, anotaciones, registro,  documento electr�nico,  en su caso, cualquier mensaje de datos permite dar certeza de que el mismo ha sido emitido por el firmante de manera tal que su contenido le es atribuible al igual que las consecuencias jur�dicas que de �l deriven." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="El usuario del RUG reconoce y acepta que los datos en forma electr�nica consignados en un mensaje de datos, o adjuntados o l�gicamente asociados al mismo por cualquier tecnolog�a, que son utilizados para identificar al usuario en relaci�n con el mensaje de datos e indicar que el usuario aprueba la informaci�n contenida en el mensaje de datos, producen los mismos efectos jur�dicos que la firma aut�grafa, siendo admisible como prueba en juicio." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="En todo momento el usuario deber�:" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="I. Cumplir las obligaciones derivadas del uso de la firma electr�nica;" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="II. Actuar con diligencia y establecer los medios razonables para evitar la utilizaci�n no autorizada de los datos de creaci�n de la firma;" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="III. Cuando se emplee un certificado en relaci�n con una firma electr�nica, actuar con diligencia razonable para cerciorarse de que todas las declaraciones que haya hecho en relaci�n con el certificado, con su vigencia, o que hayan sido consignadas en el mismo, son exactas. El firmante ser� responsable de las consecuencias jur�dicas que deriven por no cumplir oportunamente las obligaciones previstas en el presente art�culo, y" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="IV. Responder por las obligaciones derivadas del uso no autorizado de su firma." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="De conformidad con el art�culo 31 del Reglamento del Registro P�blico de Comercio" /> "<s:text name="Todo asiento quedar� realizado en el momento en que sea firmado electr�nicamente por quien lo realiza cuya fecha y hora quedar� establecida en el sello digital de tiempo contenido en la boleta que emita el Sistema" />" <s:text name=", para lo cual el usuario en todo momento en el que realiza una operaci�n con su firma electr�nica, declara lo siguiente:" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="1.	Reconozco como propia, veraz y aut�ntica la informaci�n avalada por mi Firma Electr�nica Avanzada emitida por el Servicio de Administraci�n Tributaria, la emitida por un Prestador de Servicios de Certificaci�n acreditado por la Secretar�a de Econom�a, o la emitida o reconocida por la Secretar�a de Econom�a y que la informaci�n presentada por este medio producir� los mismos efectos que las leyes otorgan a los documentos firmados aut�grafamente y, en consecuencia, tendr� el mismo valor probatorio que las disposiciones aplicables les otorgan a �stos." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="2.	Manifiesto conocer el contenido del art�culo 32 bis  4 del C�digo de Comercio que dispone:" /> "<s:text name="los acreedores, instancias de autoridad o personas facultadas que realicen inscripciones o anotaciones ser�n responsables para todos los efectos legales de la existencia y veracidad de la informaci�n y documentaci�n relativa a inscripciones y anotaciones que lleven a cabo.  Si una instituci�n financiera o persona moral autorizada realiza la inscripci�n o anotaci�n y es parte del contrato como acreedor prendario, fideicomisario, fiduciario, ser� responsable independientemente del empleado o funcionario que realiza la inscripci�n.  Los acreedores, instancias de autoridad o personas facultadas para llevar a cabo inscripciones o anotaciones en el Registro, responden por los da�os y perjuicios que se pudieran originar por tal motivo.  El afectado podr� optar por reclamar da�os y perjuicios que se le ocasionen mediante su c�lculo y acreditaci�n o por sanci�n legal.  La sanci�n legal se calcular� y exigir� en un monto equivalente a 1,000  veces el salario m�nimo diario general vigente en el Distrito Federal, sin perjuicio de las sanciones penales o administrativas a que hubiere lugar." />" 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="3.	Acepto expresamente que cualquier informaci�n inscrita de garant�as mobiliarias que se env�e a trav�s de este medio, as� como los siguientes datos personales: nombre, raz�n o denominaci�n social, tel�fono, correo electr�nico y domicilio, formar�n parte de un expediente p�blico." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="El usuario que lleva a cabo altas, bajas y cambios en la aplicaci�n de" /> "<s:text name="alta de acreedor" />" <s:text name="en el RUG, en todo momento en el que realiza dichas operaciones con su firma electr�nica, adicionalmente declara lo siguiente:" />
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="1. Declaro que cuento con poder general para actos de dominio o administraci�n otorgado por la Persona F�sica/Moral a la cual represento." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="2. Manifiesto conocer el alcance del art�culo 19 A del C�digo Fiscal de la Federaci�n, que dispone que " /> "<s:text name="se presumir� sin que se admita prueba en contrario, que los documentos digitales que contengan firma electr�nica avanzada de las personas morales, fueron presentados por el administrador �nico, el presidente del consejo de administraci�n o la persona o personas, cualquiera que sea el nombre con el que se les designe, que tengan conferida la direcci�n general, la gerencia general o la administraci�n de la persona moral de que se trate, en el momento en el que se presentaron los documentos digitales." />"
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Uso del RUG" /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Los contenidos del RUG tales como textos, gr�ficos, im�genes, logos, �conos de bot�n, software y cualquier otro contenido, deber�n utilizarse por el usuario �nicamente para los fines para los cuales ha sido creado.  El uso no autorizado del contenido del RUG  ser� sancionado en t�rminos de la legislaci�n aplicable.  De igual forma se sancionar� penalmente al que  de manera dolosa o con fines de lucro interrumpa o interfiera las comunicaciones al�mbricas, inal�mbricas o de fibra �ptica, sean telef�nicas o satelitales, por medio de las cuales se transfieran se�ales de datos." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Al hacer uso de la p�gina, el usuario se obliga a:" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="1.	Usar la p�gina conforme a los presentes T�rminos y Condiciones. " /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="2.	Realizar operaciones leg�timas, ya sean personales o a nombre de quien est� autorizado para actuar legalmente. " /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="3.	Proporcionar la informaci�n veraz, precisa y leg�tima que se le solicite para el RUG. " /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="4.	No suplantar a ninguna persona f�sica o moral, ni utilizar o proporcionar una direcci�n de correo electr�nico falsa. " /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="5.	No hacer uso indebido o inapropiado de la p�gina, tales como realizar operaciones falsas; enviar, distribuir, difundir, anunciar o divulgar informaci�n obscena, indecente, ilegal o que amenace, degrade, difame, hostigue o acose a terceros en sus bienes o en sus personas o limite el ejercicio de sus derechos; utilice recursos, medios, programas, archivos que pudieren da�ar el equipo de c�mputo de otras personas o afectar los derechos de autor, las marcas o patentes relacionadas con el software utilizado en la p�gina o en el equipo de c�mputo de terceros; utilizarla con prop�sitos comerciales o cualquier otro acto, acci�n o prop�sito que de cualquier forma da�e, impida o limite el uso del RUG. " /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="6.	Utilizar la p�gina bajo su propio riesgo conforme a los presentes T�rminos y Condiciones. " /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="El usuario puede utilizar el RUG para inscribir todo tipo de garant�as mobiliarias, privilegios especiales y derechos de retenci�n sobre bienes muebles para garantizar cualesquiera cr�ditos que una persona reciba, as� como para consultar la informaci�na trav�s del sistema mediante el cual pueden realizarse las siguientes operaciones:" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="1. Aviso Preventivo" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="2. Inscripci�n de Garant�a Mobiliaria" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="3. Modificaci�n de registro" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="4. Transmisi�n de Garant�as Mobiliaria" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="5. Rectificaci�n por error" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="6. Renovaci�n de Vigencia" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="7. Cancelaci�n de Garant�as Mobiliaria" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="8. Consulta" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="9. Solicitud de Certificaci�n" /> 
								<br>
							</td>
						</tr>	
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="De conformidad con el C�digo de Comercio, as� como del Reglamento del Registro P�blico de Comercio, los acreedores, instancias de autoridad o personas facultadas que realicen inscripciones o anotaciones sobre garant�as mobiliarias, ser�n responsables para todos los efectos legales de la existencia y veracidad de la informaci�n y documentaci�n relativa a inscripciones y anotaciones que lleven a cabo." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Si una instituci�n financiera o persona moral autorizada realiza la inscripci�n o anotaci�n y es parte del contrato como acreedor prendario, fideicomisario o fiduciario, ser� responsable, independientemente del empleado o funcionario que realiza la inscripci�n." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Ser� responsabilidad de quien realice una inscripci�n o anotaci�n llevar a cabo la rectificaci�n  de los errores materiales o de concepto que las mismas contengan.  Se entiende que se comete un  error de concepto, cuando al expresar en la inscripci�n o anotaci�n, alguno de los contenidos convencionales o formales de la garant�a o acto objeto a registro, se altere o var�e su sentido en virtud de un juicio equivocado de quien la lleve a cabo.  Todos los dem�s errores se considerar�n materiales." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Los acreedores, instancias de autoridad o personas facultadas para llevar a cabo inscripciones o anotaciones en el Registro, responden por los da�os y perjuicios que se pudieren originar por tal motivo.  El afectado podr� optar por reclamar los da�os y perjuicios que se le ocasionen mediante su c�lculo y acreditaci�n o por sanci�n legal.  La sanci�n legal se calcular� y exigir�  en un monto equivalente a 1,000  veces al Salario M�nimo Diario General vigente en el Distrito Federal." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Prohibiciones" /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Se proh�be a los usuarios violar o intentar violar la seguridad del RUG, incluyendo pero no limit�ndose a:  A) acceder a datos que no est�n destinados al usuario o entrar en un servidor o cuenta cuyo acceso no est� autorizado al usuario,  B) evaluar o probar la vulnerabilidad  de un sistema o red, o violar las medidas de seguridad o identificaci�n sin la adecuada autorizaci�n y  C) intentar impedir el servicio a cualquier usuario, anfitri�n o red, incluso mediante el env�o de virus al RUG o bloqueos del sistema." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Las violaciones de la seguridad del sistema o de la red pueden resultar en responsabilidades civiles o penales en t�rminos de la legislaci�n aplicable." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Se proh�be espec�ficamente el uso del RUG  para cualquiera de los siguientes fines:" /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="-	Usar el RUG para cualquier prop�sito distinto al fin para el cual fue creado." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="-	Impedir el adecuado funcionamiento del RUG, mediante el uso de cualquier mecanismo, software o rutina." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="-	Intentar descifrar, separar u obtener el c�digo fuente de cualquier programa de software que comprenda o constituya una parte del RUG." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="-	Interrumpir o interferir las comunicaciones al�mbricas, inal�mbricas o de fibra �ptica, sean telef�nicas o satelitales, por medio de las cuales se transfieran se�ales de datos." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="-	Intentar legar informaci�n o el sistema p�ginas rurales." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Propiedad intelectual." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Los derechos de propiedad intelectual respecto del sistema inform�tico del RUG,  as� como de todos los servicios, contenidos, derechos de explotaci�n, reproducci�n, distribuci�n, y modificaci�n o transformaci�n corresponden �nica y exclusivamente a la Secretar�a de Econom�a los usuarios del RUG, no adquieren bajo ninguna circunstancia derechos o autorizaci�n alguna para uso distinto al establecido en los presentes T�rminos y Condiciones." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Bienes y servicios de terceros enlazados." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Respecto de los servicios y contenidos que prestan terceros dentro o mediante enlace al RUG, (tales como ligas, banners y botones), el Gobierno Federal se limita exclusivamente para conveniencia del usuario a: A) Informar al usuario sobre los mismos y  B) Proporcionar un medio para poner en contacto  al usuario con terceros.  Salvo en los casos de enlaces con dependencias federales, el Gobierno Federal no es ni podr� ser considerado como proveedor de los bienes y/o servicios que se ofrecen en dichas p�ginas y/o sitios.  La inclusi�n de dichas p�ginas y/o enlaces no implica la aprobaci�n, respaldo, patrocinio, recomendaci�n o garant�a por parte del Gobierno Federal de los servicios y bienes que se comercializan en los mismos ni del contenido de dichas p�ginas, por lo tanto, el usuario ser� el responsable de revisar las pol�ticas de privacidad que requieran esos sitios de internet." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Las im�genes que aparecen tanto en la p�gina de inicio del sitio de Internet del RUG, como en todo el material de difusi�n del RUG, incluyendo tr�pticos y carteles, han sido reproducidas de conformidad con la fracci�n segunda del art�culo 148 de la Ley Federal del Derecho de Autor, en virtud de que las mismas no cuentan con un titular de derechos patrimoniales identificado, que hubiere prohibido expresamente dicha reproducci�n. En cumplimiento con lo establecido por el art�culo antes mencionado, se hacen de su conocimiento las fuentes (p�ginas de Internet) de las cuales fueron obtenidas dichas im�genes:" /> 
									<ul> 
										<li> http://diarioautomotriz.com; http://balancannoticias.blogspot.com</li>
										<li> http://portaleconomicoweb.blogspot.com </li>
										<li> http://bioseguridad.blogspot.com/2008_08_01_archive.html</li>
										<li> http://www.crisisenergetica.org/forum/viewtopic.php?howtopic=11033&mode=&show=10&page=6 </li>
										<li> http://www.nauticexpo.es/prod/sodifa-esca/maquina-tendedora-con-sistema-de-corte-br-para-astilleros-navales-31971-204182.html y</li>
										<li> http://web.softideascorp.com/Venta_manten.html</li>
									</ul>
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Uso de funciones y archivos <b>Cookies</b>." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="El usuario que tenga acceso al RUG, acuerda recibir las cookies transmitidas por los servidores del Gobierno Federal.  Una " /> "<s:text name="Cookie" />" <s:text name="es un componente que almacena datos para asegurar la confidencialidad de los datos que pasan por la red de internet, con el fin de garantizar que la informaci�n no sea observada por otros usuarios." />
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Clave de usuario y contrase�a." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="En todo momento, el usuario es el responsable �nico y final de mantener en secreto sus cuentas, contrase�as personales, claves de acceso y n�meros confidenciales con los cuales tenga acceso a los servicios y contenidos del RUG, y es el �nico  responsable del mal uso que se les d� a �stas." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Actualizaci�n de la informaci�n." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Es responsabilidad del usuario mantener actualizada la informaci�n concerniente a su persona y Garant�as Mobiliarias que haya registrado en el Sistema del RUG." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="T�rminos de uso adicionales." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="La informaci�n, datos, manifestaciones, actos y hechos jur�dicos que se deriven por el uso del RUG, estar�n sujetos a la legislaci�n que resulte aplicable, en relaci�n a cada tr�mite que se realice." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Validez Jur�dica." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Tendr�n plena validez jur�dica las inscripciones, modificaciones, transmisiones, rectificaciones de error, renovaciones de vigencia cancelaciones, anotaciones, avisos preventivos, consultas y certificaciones que se realicen en el RUG  con apego a lo dispuesto por los art�culos 32 bis  1 al 32 bis 9 y por el Cap�tulo V  del Reglamento del Registro de P�blico de Comercio." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Modificaciones." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Tendr�n plena validez jur�dica las inscripciones, modificaciones, transmisiones, rectificaciones de error, renovaciones de vigencia cancelaciones, anotaciones, avisos preventivos, consultas y certificaciones que se realicen en el RUG  con apego a lo dispuesto por los art�culos 32 bis  1 al 32 bis 9 y por el Cap�tulo V  del Reglamento del Registro de P�blico de Comercio." /> 
								<br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="El Gobierno Federal tendr� el derecho de modificar en cualquier momento el contenido y alcance de los presentes T�rminos y Condiciones lo cual se har� del conocimiento del usuario de manera oportuna, por lo cual deber� leerlos atentamente cada vez que se le haga de conocimiento y pretenda utilizar el RUG." /> 
								<br>
							</td>
						</tr>
						
						<tr>
							<td align="left" class="tituloInteriorRojo">
								<br><s:text name="Leyes aplicables y Jurisdicci�n en caso de controversia." /><br>
							</td>
						</tr>
						<tr>
							<td align="justify" class="texto_general">		
								<br>
									<s:text	name="Para la interpretaci�n, cumplimiento, ejecuci�n as� como para el caso de controversia derivada de la interpretaci�n o cumplimiento de los presentes T�rminos y Condiciones, de las Pol�ticas de Privacidad o de cualquier otro documento relevante del sitio de internet del RUG ser�n aplicables las leyes federales de los Estados Unidos Mexicanos y competentes los tribunales de la Ciudad de M�xico, Distrito Federal, renunciando expresamente a cualquier otro fuero o jurisdicci�n que pudiera corresponderle en raz�n de su domicilio presente o futuro." /> 
								<br>
							</td>
						</tr>
					</table>

							</div>		
						</td>
					</tr>
					<tr>
						<td class="terminos" colspan="2">
							<br /><br />
							<s:fielderror fieldName="registroUsuario.terminos" />
							<s:checkbox name="registroUsuario.terminos" value="registroUsuario.terminos"
									cssClass="campo_general" />
							<s:text name="usuario.field.leido" />&nbsp;														
							<s:url id="uri" action="help.do" encode="true" namespace="/comun/publico">
								<s:param name="llave">terminosUsoModal</s:param>
								<s:param name="keepThis">true</s:param>
								<s:param name="TB_iframe">true</s:param>
								<s:param name="height">500</s:param>
								<s:param name="width">750</s:param>
							</s:url> 																					
							
							<a onclick="showTerminos()" style="cursor: pointer;"><u><s:text name="common.terminos" /></u></a>
							
							<s:text name="usuario.field.ylas" />&nbsp;
							<s:url id="uri" action="help.do" encode="true" namespace="/comun/publico">
								<s:param name="llave">politicasPrivacidadModal</s:param>
								<s:param name="keepThis">true</s:param>
								<s:param name="TB_iframe">true</s:param>
								<s:param name="height">500</s:param>
								<s:param name="width">750</s:param>
							</s:url> 
							<a onclick="showPoliticas()" style="cursor: pointer;"><u><s:text name="common.politicas" /></u></a>
						</td>
					</tr>
					
					<tr>
						<td class="btnSubmit" colspan="2">
							<s:url var="uri"  value="%{getContextPath()}/imgs/%{getText('common.img.i18n.path')}/aceptar.gif" />
					
							<s:submit type="button" src="%{uri}" name="Aceptar" value="Aceptar" align="center" />
						</td>
						
					</tr>
				</table>			
			</td>
			<td width="15%" valign="top">
<!--				<img width="162" src="<%= request.getContextPath() %>/imgs/imgrecortada_jugueteria.jpg" /> -->
			</td>			
		</tr>			
	</table>
</s:form>
<script type="text/javascript">
function showTerminos() {
    var URL="<%=request.getContextPath()%>/comun/publico/help.do?llave=terminosUsoModal";
    window.open(URL,"ventana1","width=500,height=750,scrollbars=1");
}
function showPoliticas() {
    var URL="<%=request.getContextPath()%>/comun/publico/help.do?llave=politicasPrivacidadModal";
    window.open(URL,"ventana1","width=500,height=750,scrollbars=1");
}
</script>