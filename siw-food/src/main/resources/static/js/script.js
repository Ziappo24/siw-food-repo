  
  // Ottieni l'elemento del contenitore con l'ID "container" dal DOM
  const container = document.getElementById('container');
  
  // Ottieni il pulsante di registrazione con l'ID "register" dal DOM
  const registerButton = document.getElementById('register');
  
  // Ottieni il pulsante di accesso con l'ID "login" dal DOM
  const loginButton = document.getElementById('login');

  // Aggiungi un listener per l'evento di click sul pulsante di registrazione
  registerButton.addEventListener('click', () => {
    // Quando il pulsante di registrazione viene cliccato, aggiungi la classe "active" all'elemento del contenitore
    container.classList.add("active");
  });

  // Aggiungi un listener per l'evento di click sul pulsante di accesso
  loginButton.addEventListener('click', () => {
    // Quando il pulsante di accesso viene cliccato, rimuovi la classe "active" dall'elemento del contenitore
    container.classList.remove("active");
  });
