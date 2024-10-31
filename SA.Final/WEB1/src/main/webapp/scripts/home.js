document.addEventListener('DOMContentLoaded', function() {
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

// Seleciona os elementos
const modal = document.getElementById("taskModal");
const openModalBtn = document.querySelector(".add-task-btn");
const closeModalBtn = document.querySelector(".close");

// Abre o modal ao clicar no botão
openModalBtn.addEventListener("click", function(event) {
    event.preventDefault();
    modal.style.display = "block";
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

