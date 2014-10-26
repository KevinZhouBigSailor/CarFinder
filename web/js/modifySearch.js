/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function modifySearch(event)
{
 event.preventDefault();
 
  // Get some values from elements on the page:
  var $form = $( this ),
    term = $form.find("input:checkbox[name='type']:checked").val(),
    url = $form.attr( "action" );
    
    
    
    
}
