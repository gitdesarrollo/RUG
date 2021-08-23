<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Acto o contrato que crea la obligaci�n garantizada es el mismo que crea la Garant�a Mobiliaria." /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="Si la Garant�a Mobiliaria se encuentra contenida en el mismo documento que la obligaci�n principal seleccione este campo." /> 
			<br>
		</td>
	</tr>
		
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>*</b>	El Cr�dito Refaccionario  (Art�culo 324 LGTOC)" /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>*</b>	El Cr�dito de Habilitaci�n o Av�o (Art�culo 321 LGTOC)" /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>*</b>	Arrendamiento Financiero (Art�culo 408 LGTOC)" /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>*</b>	Cl�usula de Reserva de Dominio en una Compraventa Mercantil  (Art�culo 2312 CCF)" /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>*</b>   Derechos de retenci�n y Privilegios Especiales (en la mayor�a de los casos)" /> 
			<br>
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
				<s:text	name=" <b>Art. 33.-</b>  Para que proceda un Asiento en el RUG deber� cumplimentarse toda la informaci�n que sea identificada como obligatoria en las pantallas del Sistema, misma que ser� para todos los efectos a que haya lugar la contenida en las formas precodificadas. Para efectos de este art�culo, la Secretar�a deber� publicar en el Diario Oficial de la Federaci�n los formatos correspondientes a la informaci�n que se solicite en las pantallas del Sistema." /> 
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
</table>
