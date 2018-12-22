
window.appendOneMaterial = function (nIndex) {


    // Добавляем кнопки
    let id_edit = 'button_mat_edit_' + nIndex;
    let id_remove = 'button_mat_remove_' + nIndex;
    let oButtonEdit = document.createElement('button');

    oButtonEdit.id = id_edit;
    oButtonEdit.className = 'buttonMaterialEdit';
    oButtonEdit.innerHTML = 'Редактировать';

    let oButtonRemove = document.createElement('button');
    oButtonRemove.id = id_remove;
    oButtonRemove.className = 'buttonMaterialRemove';
    oButtonRemove.innerHTML = 'Удалить';

    materials.appendChild(oButtonEdit);
    materials.appendChild(oButtonRemove);
}