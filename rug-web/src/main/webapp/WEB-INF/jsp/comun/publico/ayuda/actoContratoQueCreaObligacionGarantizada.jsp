<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Acto o Contrato que crea la Obligaci�n Garantizada." /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="Ingrese la informaci�n de la obligaci�n principal que se garantiza mediante la Garant�a Mobiliaria." /> 
			<br><br>
		</td>
	</tr>
	<tr>
		<td class="tituloInteriorRojo">	
			<br class="tituloInteriorRojo"><s:text name="Fundamento Legal" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" />
			<br>
		</td>	
	</tr>
	<tr>
		<td  class="titulo_interior">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf");
				class="ver_todas3" target="_blank"> <s:text name="tipo.empresa.ayuda.reglamento.registro.publico.comercio" />
				</a>
			<br>
		</td>	
	</tr>	
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="<b>Art. 33.-</b>  Para que proceda un Asiento en el RUG deber� cumplimentarse toda la informaci�n que sea identificada como obligatoria en las pantallas del Sistema, misma que ser� para todos los efectos a que haya lugar la contenida en las formas precodificadas. Para efectos de este art�culo, la Secretar�a deber� publicar en el Diario Oficial de la Federaci�n los formatos correspondientes a la informaci�n que se solicite en las pantallas del Sistema." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="Siempre que deban ser  inscritos instrumentos jur�dicos, se entender� que los mismos quedan inscritos en el RUG mediante el llenado de la informaci�n sobre los mismos que requiera las pantallas del Sistema y la generaci�n del Asiento." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Ejemplo" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>	
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="Contrato de cr�dito simple n�mero 00012352221545, de fecha 5 de septiembre de 2010." /> 
		</td>
	</tr>
</table>
