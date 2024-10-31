/*document.addEventListener('DOMContentLoaded', function() {
    // Selecionar todos os links da sidebar
    const sidebarLinks = document.querySelectorAll('.sidebar-link');

    // Adicionar evento de clique para cada link
    sidebarLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // Evita comportamento padrão do link

            // Remover a classe 'active' de todos os links
            sidebarLinks.forEach(link => link.classList.remove('active'));

            // Adicionar a classe 'active' ao link clicado
            this.classList.add('active');
        });
    });
});
*/

// MODAL TASKS
const modal = document.getElementById("taskModal");
const modalTitle = document.getElementById("modalTitle");
const submitButton = document.getElementById("submitButton");
const openModalBtn = document.querySelector(".add-task-btn");
const closeModalBtn = document.querySelector(".close");
const editTaskIcons = document.querySelectorAll(".fa-pen-to-square");

// Função para abrir o modal com o título e ação corretos
function openModal(isEdit = false, taskData = {}) {
    modal.style.display = "block";
    if (isEdit) {
        modalTitle.textContent = "Editar Tarefa";
        submitButton.textContent = "Salvar Alterações";
        // Preenche os campos com os dados da tarefa
        document.getElementById("taskTitle").value = taskData.title || "";
        document.getElementById("taskDescription").value = taskData.description || "";
        document.getElementById("taskDate").value = taskData.date || "";
    } else {
        modalTitle.textContent = "Nova Tarefa";
        submitButton.textContent = "Criar Tarefa";
        // Limpa os campos para criação de nova tarefa
        document.getElementById("taskForm").reset();
    }
}

// Abre o modal ao clicar no botão "Nova Tarefa"
openModalBtn.addEventListener("click", function(event) {
    event.preventDefault();
    openModal();
});

// Abre o modal ao clicar no ícone de edição
editTaskIcons.forEach(icon => {
    icon.addEventListener("click", function(event) {
        event.preventDefault();
        // Recupera os dados da tarefa. Exemplo fictício:
        const taskData = {
            title: "Título da Tarefa Existente",
            description: "Descrição da Tarefa Existente",
            date: "2024-03-23T10:00"
        };
        openModal(true, taskData);
    });
});

// Fecha o modal ao clicar no "X"
closeModalBtn.addEventListener("click", function() {
    modal.style.display = "none";
});

// Fecha o modal ao clicar fora do conteúdo
window.addEventListener("click", function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});





// MODAL STATUS
const statusModal = document.getElementById("statusModal");
const statusSelect = document.getElementById("statusSelect");
const statusIcons = document.querySelectorAll(".fa-rectangle-list");
const closeStatusModalBtn = statusModal.querySelector(".close");

// Função para abrir o modal de status
function openStatusModal(currentStatus = "pendente") {
    statusModal.style.display = "block";
    statusSelect.value = currentStatus;
}

// Abre o modal de status ao clicar no ícone de status
statusIcons.forEach(icon => {
    icon.addEventListener("click", function(event) {
        event.preventDefault();
        // Definir status atual da tarefa (exemplo fictício):
        const currentStatus = "progresso"; // Esse valor pode vir dinamicamente
        openStatusModal(currentStatus);
    });
});

// Fecha o modal de status ao clicar no "X"
closeStatusModalBtn.addEventListener("click", function() {
    statusModal.style.display = "none";
});

// Fecha o modal de status ao clicar fora do conteúdo
window.addEventListener("click", function(event) {
    if (event.target === statusModal) {
        statusModal.style.display = "none";
    }
});

// Submete o formulário de mudança de status
document.getElementById("statusForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const selectedStatus = statusSelect.value;
    // Ação para atualizar o status da tarefa com o `selectedStatus`
    console.log("Status selecionado:", selectedStatus);
    statusModal.style.display = "none";
});






// MMODAL TRASH
const deleteModal = document.getElementById("deleteModal");
const deleteIcons = document.querySelectorAll(".fa-trash");
const closeDeleteModalBtn = deleteModal.querySelector(".close");
const confirmDeleteBtn = document.getElementById("confirmDelete");
const cancelDeleteBtn = document.getElementById("cancelDelete");

// Função para abrir o modal de confirmação de exclusão
function openDeleteModal(taskId) {
    deleteModal.style.display = "block";
    // Salva o ID da tarefa a ser deletada (pode ser usado para a ação real de exclusão)
    confirmDeleteBtn.setAttribute("data-task-id", taskId);
}

// Abre o modal de exclusão ao clicar no ícone de lixeira
deleteIcons.forEach(icon => {
    icon.addEventListener("click", function(event) {
        event.preventDefault();
        const taskId = this.getAttribute("data-task-id"); // Exemplo de ID da tarefa
        openDeleteModal(taskId);
    });
});

// Fecha o modal de exclusão ao clicar no "X" ou no botão de cancelar
closeDeleteModalBtn.addEventListener("click", function() {
    deleteModal.style.display = "none";
});
cancelDeleteBtn.addEventListener("click", function() {
    deleteModal.style.display = "none";
});

// Confirmação da exclusão
confirmDeleteBtn.addEventListener("click", function() {
    const taskId = this.getAttribute("data-task-id");
    // Ação para excluir a tarefa com o ID `taskId`
    console.log("Excluindo tarefa com ID:", taskId);
    deleteModal.style.display = "none";
});

// Fecha o modal ao clicar fora do conteúdo
window.addEventListener("click", function(event) {
    if (event.target === deleteModal) {
        deleteModal.style.display = "none";
    }
});


