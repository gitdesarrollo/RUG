<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Criterios de B�squeda" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="Toda persona podr� realizar consultas y solicitar certificaciones." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br>
				<s:text name="Se pueden realizar b�squedas por uno o varios criterios: " />
			<br>
		</td>
	</tr>		
	
	<tr>
		<td>	
			<br class="texto_general"><s:text name="i) Por la Descripci�n de los Bienes Muebles;"/><br>
		</td>	
	</tr>	
	<tr>
		<td>	
			<br class="texto_general"><s:text name="ii) Por el nombre del Otorgante de la Garant�a Mobiliaria;" /><br>
		</td>
	</tr>	
	<tr>
		<td>	
			<br class="texto_general"><s:text name="iii) Por folio electr�nico del Otorgante de la Garant�a Mobiliaria;" /><br>
		</td>
	</tr>
	<tr>
		<td>	
			<br class="texto_general"><s:text name="iv) Por el N�mero de Garant�a Mobiliaria o de Asiento." /><br>
		</td>
	</tr>	
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Fundamento Legal" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td  class="texto_general">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf"
				class="ver_todas3" target="_blank"> <b><s:text name="Reglamento del Registro p�blico de Comercio	" /></b>
				</a>
			<br>
		</td>	
	</tr>		
	<tr>
		<td>			
			<br class="texto_general">
				<s:text	name="<b>Art�culo 34.-</b> Toda persona podr� realizar consultas y solicitar la emisi�n de certificaciones de Asientos que consten en el RUG." />
			 <br>
		</td>
	</tr>	
	<tr>
		<td>			
			<br class="texto_general">
				<s:text	name="La certificaci�n ser� emitida con firma electr�nica y sello digital de tiempo y contendr�, al igual que la boletas que emita el Sistema, una cadena �nica de datos que podr� ser ingresada en el sistema para verificar su autenticidad." />
			 <br>
		</td>
	</tr>
	<tr>
		<td>			 
			<br class="texto_general"> 
				<s:text name="En toda consulta y certificaci�n el RUG proporcionar� la siguiente informaci�n:" />
			<br>
		</td>	
	</tr>		
	<tr>
		<td>			
			<br class="texto_general"> 
				<s:text name="I. Nombre, denominaci�n o raz�n social del Acreedor, seg�n sea el caso;" />
			<br>
		</td>	
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="II. Nombre, denominaci�n o raz�n social del Otorgante, seg�n sea el caso;" />
			<br>
		</td>	
	</tr>	
	<tr>
		<td>
		
			<br class="texto_general"> 	
				<s:text name="III. Nombre, denominaci�n o raz�n social del deudor, en aquellos casos en que �ste �ltimo sea distinto del Otorgante;" />
			<br>
		</td>	
	</tr>	
	<tr>
		<td>	
			<br class="texto_general"> 	
				<s:text name="IV. Folio del Otorgante;" />
			<br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general"> 	
				<s:text name="V.	Tipo de Garant�a Mobiliaria;" />
			<br>
		</td>
	</tr>	
	<tr>
		<td>	
			<br class="texto_general"> 	
				<s:text name="VI. Descripci�n de los bienes muebles objeto de la Garant�a Mobiliaria;" />
			<br>
		</td>
	</tr>	
	<tr>
		<td>	 
			<br class="texto_general"> 	
				<s:text name="VII. Fecha y hora de la inscripci�n de la Garant�a Mobiliaria y de la certificaci�n;" />
			<br>
		</td>
	</tr>	
	<tr>
		<td>	
			<br class="texto_general"> 	
				<s:text name="VIII. N�mero de la Garant�a Mobiliaria, que le haya asignado el Sistema; y" />
			
		</td>	
	</tr>		
	<tr>
		<td>	
			<br class="texto_general"> 	
				<s:text name="IX Los Asientos de Garant�as Mobiliarias vigentes." />
		</td>	
	</tr>
	
</table>
