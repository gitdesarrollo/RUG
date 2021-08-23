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
				<s:text	name="El Otorgante es la persona que otorga una garant�a mobiliaria. Usualmente coincide con el Deudor." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de una prenda sin transmisi�n de posesi�n, el Otorgante ser�a el Deudor Prendario." />
			<br>
		</td>
	</tr>		
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de una hipoteca industrial, el Otorgante ser�a el Deudor Hipotecario." />
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de un cr�dito refaccionario de habilitaci�n o av�o, el Otorgante ser�a el Acreditado (Deudor)." />
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de una compraventa con reserva de dominio, el Otorgante ser�a el Comprador." />
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de un Arrendamiento Financiero, el Otorgante ser�a el Arrendatario." />
			<br>
		</td>
	</tr>	
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de un Fideicomiso de Garant�a, el Otorgante ser�a el Fideicomitente que constituy� el fideicomiso para garantizar una obligaci�n principal." />
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Trat�ndose de privilegios especiales o derechos de retenci�n, el Otorgante ser�a la persona contra la cual se pueden ejercer dichos derechos." />
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br class="texto_general">
				<s:text name="Ingrese los datos de la persona que otorga una Garant�a Mobiliaria." />
			<br>
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
				class="ver_todas3" target="_blank"> <b><s:text name="Reglamento del Registro P�blico de Comercio" /></b>
				</a>
			<br>
		</td>	
	</tr>	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b>Art�culo 1.-</b> El presente ordenamiento establece las normas reglamentarias a que se sujetar� la prestaci�n del servicio del Registro P�blico de Comercio." /><br />
		</td>	
	</tr>	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="Para efecto de este Reglamento se entiende por:" /><br />
		</td>	
	</tr>	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="..." /><br />
		</td>	
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b>IV. Otorgante:</b> La persona que otorga una Garant�a Mobiliaria;" /><br />
		</td>	
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name=" ..." /><br />
		</td>	
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b>Art�culo 33 Bis 2.-</b> En la inscripci�n deber� identificarse en el Sistema la siguiente informaci�n:" /><br />
		</td>	
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="I.	El Otorgante y, en su caso, el o los deudores;" /><br />
		</td>	
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name=" ..." /><br>
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
		<td>		
			<br class="texto_general">
				<s:text name="El Otorgante de Garant�as Mobiliarias, S.A. de C.V." /><br />
		</td>	
	</tr>
</table>
