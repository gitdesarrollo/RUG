<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="N�mero de Garant�a Mobiliaria" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="El n�mero de garant�a mobiliaria es el n�mero que el sistema asigna a cada garant�a al quedar inscrita en el RUG." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<br>
				<s:text name="Ingrese el n�mero que fue asignado por el sistema del RUG a una Garant�a Mobiliaria al momento de su inscripci�n." />
			<br>
		</td>
	</tr>		
		
	<tr>
		<td>	
			<br class="tituloInteriorRojo"><s:text name="Fundamento Legal" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" />
			<br>
		</td>	
	</tr>	
	<tr>
		<td  class="texto_general">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf"
				class="ver_todas3" target="_blank"> <b><s:text name="tipo.empresa.ayuda.ley.genreal.mercantiles" /></b>
				</a>
			<br>
		</td>	
	</tr>		
	<tr>
		<td>			
			<br class="texto_general">
				<s:text	name="Art�culo 34.- Toda persona podr� realizar consultas y solicitar la emisi�n de certificaciones de Asientos que consten en el RUG. La certificaci�n ser� emitida con firma electr�nica y sello digital de tiempo y contendr�, al igual que la boletas que emita el Sistema, una cadena �nica de datos que podr� ser ingresada al mismo para verificar su autenticidad. " />
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
				<s:text name="......." />
			<br>
		</td>	
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="VIII. N�mero de la Garant�a Mobiliaria, que le haya asignado el Sistema" />
			<br>
		</td>	
	</tr>	
	
</table>
