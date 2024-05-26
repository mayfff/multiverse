// login-register
const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-popup');
const iconClose = document.querySelector('.icon-close');

if (wrapper && loginLink && registerLink && btnPopup && iconClose) {
    registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
    });

    loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
    });

    btnPopup.addEventListener('click', ()=> {
    wrapper.classList.add('active-popup');
    });

    iconClose.addEventListener('click', ()=> {
    wrapper.classList.remove('active-popup');
    });
}



// modal buy ticket

const modalPopup = document.getElementById('popup');
const span = document.getElementsByClassName('close-icon')[0];
// const modalTitle = document.querySelector('.modal-title');
const btns = document.querySelectorAll('.buy-ticket-btn');

if (modalPopup && span && btns.length > 0) {
    btns.forEach(btn =>  {
        btn.addEventListener('click', function() {
            // const movieTitle = this.parentElement.getAttribute('data-title');
            // modalTitle.innerText = movieTitle;
            modalPopup.style.display = 'block';
        });
    });
    span.onclick = function() {
    modalPopup.style.display = 'none';
    };

}


// open profile using button login

const loginForm = document.getElementById('loginForm');

if (loginForm) {
    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        window.location.href = "profile.html";
    });
}
