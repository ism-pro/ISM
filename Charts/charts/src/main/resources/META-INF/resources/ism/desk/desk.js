/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The onclick handler for HtmlShowOneDeckRenderer.
 *
 * @param formClientId  the clientId of the enclosing UIForm component
 * @param clientId      the clientId of the ProShowOneDeck component
 * @param itemId        the id of the UIShowItem that was clicked
 */


function _DeskActiveTab(formClientId, clientId, itemId) {
    var form = document.forms[formClientId];
    var input = form[formClientId];
    if (!input) {
        input = document.createElement("input");
        input.type = 'hidden';
        input.name = clientId;
        form.appendChild(input);
    }
    input.value = itemId;
}
$(function () {

});

