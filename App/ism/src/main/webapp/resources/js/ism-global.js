/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*******************************************************************************
 * Global
 * 
 ******************************************************************************/
function showStatus() {
    PF('statusDialog').show();
}
function hideStatus() {
    PF('statusDialog').hide();
}



/*******************************************************************************
 * 
 *  Main JS Application
 * 
 ******************************************************************************/
$(function () {    
    $(document).ready(function(e){
        // Bootstrap on ready enable tooltip
        $('[data-toggle="tooltip"]').tooltip();
    });
}); 


