<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Acreedores Adicionales" /><br>
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /></p>
		</td>
	</tr>
	<tr>
		<td align="justify">			 
			<br class="texto_general"> 
				<s:text name="Acreedor es la persona en cuyo favor se otorga una Garant�a Mobiliaria." />
			<br>
		</td>	
	</tr>				
	<tr>
		<td align="justify">			 
			<br class="texto_general"> 
				<s:text name="Agregue la informaci�n de las dem�s personas en cuyo favor se otorga la Garant�a Mobiliaria, si los hubiere." />
			<br>
		</td>	
	</tr>
	<tr>
		<td align="justify">			 
			<br class="texto_general"> 
				<s:text name="Deber�n agregarse como "/> "Acreedores Adicionales" <s:text name=" los Acreedores de un cr�dito sindicado o fideicomisarios de un fideicomiso de garant�a, distintos del Acreedor o fideicomisario que inscribe la Garant�a Mobiliaria." />
			<br>
		</td>	
	</tr>
</table>
