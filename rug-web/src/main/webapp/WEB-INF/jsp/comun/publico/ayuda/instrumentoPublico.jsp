<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Datos del instrumento p�blico mediante el cual se formaliz� el Acto o Contrato" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="Indique el n�mero de instrumento p�blico cuando la operaci�n haya sido formalizada ante fedatario p�blico." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">	
			<br class="texto_general">
				<s:text name="En el caso dela prenda sin transmisi�n de posesi�n, el articulo 365 Ley General de T�tulos y Operaciones de Cr�dito dispone que cuando el monto de se refiera a bienes cuyo monto sea igual o superior al equivalente en moneda nacional a doscientos cincuenta mil Unidades de Inversi�n, las partes deber�n ratificar sus firmas ante fedatario." />
			<br>
		</td>
	</tr>		
			
	<tr>
		<td class="tituloInteriorRojo">	
			<br class="tituloInteriorRojo"><s:text name="Ejemplo" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" />
			<br>
		</td>	
	</tr>	
	
		<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text name="Mediante instrumento p�blico n�mero [n�mero del instrumento p�blico] de fecha [d�a] de [mes] del [a�o], otorgada ante la fe del Lic. [nombre del fedatario p�blico], [Corredor/Notario] P�blico n�mero [n�mero de fedatario p�blico] de [Nombre de la Entidad Federativa], se hizo constar la ratificaci�n de firmas del contrato de [nombre del contrato de Garant�a mobiliaria] celebrado con esa misma fecha entre [nombre del acreedor], en su car�cter de acreedor, y [nombre del otorgante de la Garant�a mobiliaria], en su car�cter de otorgante de [car�cter del otorgante de la Garant�a Mobiliaria]." /><br />
			<br>
		</td>	
	</tr>
</table>
