<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="N�mero de Garant�a Mobiliaria o Asiento" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="El N�mero de Garant�a Mobiliaria es el n�mero que el sistema asigna a cada garant�a cuando queda inscrita en el RUG." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">
			<br>
				<s:text name="El n�mero de Asiento es el n�mero que el RUG asigna a todas las operaciones distintas de la Inscripci�n (Modificaci�n, Transmisi�n, Renovaci�n, Rectificaci�n, Cancelaci�n, Aviso Preventivo y Anotaci�n." />
			<br>
		</td>
	</tr>		
	
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Fundamento Legal" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td  class="texto_general">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf"
				class="ver_todas3" target="_blank"> <b><s:text name="Reglamento del Registro Publico de Comercio" /></b>
				</a>
			<br>
		</td>	
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="<b>Art�culo 30.-</b> El registro de los actos en los que se haga constar la constituci�n, modificaci�n, transmisi�n o cancelaci�n de Garant�as Mobiliarias, se efectuar� en el RUG conforme a lo previsto en el C�digo de Comercio y estar� sujeto a lo previsto en los cap�tulos I, salvo lo relativo a las formas precodificadas, el presente cap�tulo y VII de este Reglamento." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="..." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="Para efectos del presente cap�tulo, el <b>Asiento</b> es la inscripci�n de una Garant�a Mobiliaria, su modificaci�n, transmisi�n, renovaci�n, rectificaci�n, cancelaci�n, as� como los avisos preventivos y las anotaciones." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name=" <b>Art�culo 34.-</b>En toda consulta y certificaci�n el RUG proporcionar� la siguiente informaci�n:" /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="..." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name=" VIII. N�mero de la Garant�a Mobiliaria, que le haya asignado el Sistema" /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name=" IX. Los Asientos de Garant�as Mobiliarias vigentes" /> 
			<br>
		</td>
	</tr>
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Ejemplo" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="140" /> 
			<br>
		</td>
	</tr>
</table>
