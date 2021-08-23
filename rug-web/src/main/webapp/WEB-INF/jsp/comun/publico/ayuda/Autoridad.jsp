<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Autoridad que instruye la Inscripci�n y Resoluci�n Judicial o Administrativa en la cual se funda la misma  " /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="Ingrese los datos de la Autoridad Judicial o Administrativa que orden� la Inscripci�n, los datos de la Resoluci�n Judicial  o Administrativa, as� como el texto que debe ser anotado en virtud de la misma." /> 
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
		<td>			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/legislacion/CODIGO DE COMERCIO.pdf");
				class="ver_todas3" target="_blank"> <s:text name="C�digo de Comercio" />
				</a>
			<br>
		</td>	
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b>Art. 32 bis 4.</b>" /> 
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
		<td align="justify">		
			<br class="texto_general">
				<s:text	name=" Se encuentran facultados para llevar a cabo inscripciones o anotaciones en el Registro, los fedatarios p�blicos, los jueces y las oficinas habilitadas de la Secretaria en las entidades federativas, as� como las entidades financieras, los servidores p�blicos y otras personas que para tales fines autorice la Secretaria." /> 
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
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Ejemplo" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name=" Licenciado Francisco Rodr�guez Hern�ndez, Juez D�cimo Cuarto de lo Mercantil del Primer Partido Judicial en el Estado de Jalisco." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name=" Oficio 222/10 Expediente 2288/2010. Dentro de los autos del Juicio Mercantil Ejecutivo promovido por Ricardo Ugalde Garc�a en contra de REFACCIONES �TILES Y GRANOS, S.A. DE C.V., se orden� girar a usted atento oficio como tengo el honor de hacerlo a efecto de que se sirva llevar a cabo la inscripci�n dentro del Registro �nico de Garant�as Mobiliarias, secci�n del Registro P�blico de Comercio, del embargo trabado en autos." /> 
			<br>
		</td>
	</tr>
</table>
