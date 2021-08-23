<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Tipo de Bienes Muebles objeto de la Garant�a Mobiliaria" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="Para efectos del RUG los bienes muebles que pueden ser objeto de una garant�a mobiliaria son:" /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Maquinaria y equipo;" /> 
			<br>
		</td>
	</tr>	
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Veh�culos de motor; " /> 
			<br>
		</td>
	</tr>	
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Ganado " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Productos agr�colas; " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Bienes de consumo;  " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Inventario;  " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Derechos, incluyendo derechos de cobro;   " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Derechos previstos en la Ley de la Propiedad Industrial y en la Ley Federal del Derecho de Autor;   " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> Acciones y obligaciones bonos, contratos de opci�n Futuros;    " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="<b> *</b> y Otros.  " /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text	name="Usted podr� seleccionar uno o varios tipos de Bienes Muebles  " /> 
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
				class="ver_todas3" target="_blank"> <s:text name="Reglamento del Registro Publico de Comercio" />
				</a>
			<br>
		</td>	
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="<b>Art�culo 32.-</b> Para efectos del RUG, los criterios de clasificaci�n de las Garant�as Mobiliarias y de los bienes muebles afectos a las mismas ser�n los siguientes:" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="..." />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="B. Los bienes muebles que pueden ser objeto de una Garant�a Mobiliaria se clasifican de la siguiente manera:" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XIX.	Maquinaria y equipo;" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XX.	Veh�culos de motor;" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXI.	Ganado; " />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXII.	Productos agr�colas; " />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXIII. Bienes de consumo;" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXIV.	Inventario;" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXV.	Acciones y obligaciones, bonos, contratos de opci�n y futuros;" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXVI.	Derechos, incluyendo derechos de cobro; y" />
			 <br>
		</td>
	</tr>
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="XXVII. Otros." />
			 <br>
		</td>
	</tr>
</table>
