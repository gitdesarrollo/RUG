<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%" border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Tipo de Acto o Contrato que da origen a la operaci�n" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="ADVERTENCIA: Si con motivo de la rectificaci�n cambia al Otorgante, la Garant�a Mobiliaria se asentar� en el folio electr�nico del nuevo Otorgante y en el folio del Otorgante original se incluir� un asiento indicando <b>Cancelada por Transmisi�n.</b>" /> 
			<br>
		</td>
	</tr>
	
</table>
