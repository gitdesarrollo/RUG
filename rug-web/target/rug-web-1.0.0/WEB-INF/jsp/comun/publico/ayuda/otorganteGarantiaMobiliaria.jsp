<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Otorgante de la Garant�a Mobiliaria" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>ADVERTENCIA:</b>Si con motivo de la transmisi�n cambia al Otorgante, la Garant�a Mobiliaria se transmitir� al folio electr�nico del nuevo Otorgante y en el folio del Otorgante original se incluir� un asiento indicando <b>Cancelada por Transmisi�n</b>." /> 
			<br>
		</td>
	</tr>
		
	<tr>
		<td  >			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/legislacion/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf");
				class="ver_todas3" target="_blank"> <s:text name="Reglamento del Registro Publico de Comercio" />
				</a>
			<br>
		</td>	
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>Art�culo 33 Bis.-</b>Las operaciones que se pueden realizar en el RUGson las siguientes:" /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name=" ..." /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>VI.</b> Modificaci�n, transmisi�n, rectificaci�n por error y cancelaci�n de Garant�as Mobiliarias, as� como  renovaci�n de inscripci�n." /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name=" ..." /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name=" Se entiende por transmisi�n, el Asiento de un acto mercantil que agregue, elimine o modifique al Otorgante y/o al o los Acreedores. Si la transmisi�n es respecto del Otorgante, se asentar� una cancelaci�n por transmisi�n en el folio del Otorgante original y una inscripci�n por transmisi�n en el folio del nuevo Otorgante." /> 
			<br>
		</td>
	</tr>
	
</table>
