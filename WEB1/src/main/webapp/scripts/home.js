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
