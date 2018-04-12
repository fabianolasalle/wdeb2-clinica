/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function redireciona(param, id, valorId, nome, valorNome, email, valorEmail, endereco, valorEndereco){
  location.href=param+"?"+id+"="+valorId+"&"+nome+"="+valorNome+"&"+email+"="+valorEmail+"&"+endereco+"="+valorEndereco;
}




function mascaraData( campo, e )
{
	var kC = (document.all) ? event.keyCode : e.keyCode;
	var data = campo.value;
	
	if( kC!=8 && kC!=46 )
	{
		if( data.length==2 )
		{
			campo.value = data += '/';
		}
		else if( data.length==5 )
		{
			campo.value = data += '/';
		}
		else
			campo.value = data;
	}
}
