(function (){
    const app = {
        btnSubmit : document.querySelector('#formQuest .btn-submit'),
        btnRestart : document.querySelector('#formQuest .btn-restart'),
        currentAnswer : 0,
        btnSubmitShow: function () {
            if (app.btnSubmit.classList.contains('hidden')) {
                app.btnSubmit.classList.remove('hidden')
            }
            if (app.btnSubmit.hasAttribute('disabled')) {
                app.btnSubmit.removeAttribute('disabled')
            }
        },
        btnSubmitHide: function () {
            if (!app.btnSubmit.classList.contains('hidden')) {
                app.btnSubmit.classList.add('hidden')
            }
        },
        btnSubmitBlock: function () {
            if (!app.btnSubmit.hasAttribute('disabled')) {
                app.btnSubmit.setAttribute('disabled', true)
            }
        },
        btnRestartShow: function () {
            if (app.btnRestart.classList.contains('hidden')) {
                app.btnRestart.classList.remove('hidden')
            }
        },
        addListenerRadioInputs: function () {
            let nodeList = document.querySelectorAll('#formQuest input[type=radio]');
            if (nodeList.length > 0) {
                nodeList.forEach(elm => elm.addEventListener('change', function (el) {
                    app.btnSubmitShow();
                    app.currentAnswer = this.value;
                }));
            }
        }
    };


    if(document.querySelector('#formQuest') !== null) {
        app.addListenerRadioInputs();
        document.querySelector('#formQuest').addEventListener('submit', function (e) {
            e.preventDefault();
            console.log('submit');
            console.log(this);
            console.log(e);

            app.btnSubmitBlock();
            // let formData = new FormData(this);
            // axios.post(document.location.href, formData)
            axios.post(document.location.href, {action: 'nextStage', answer: app.currentAnswer})
                .then(function (response) {
                    document.querySelector('.text-question').innerHTML = response.data.question.text
                    if (response.data.question.isFinal) {
                        app.btnRestartShow();
                        document.querySelector('.list-answers').innerHTML = '';
                    } else {
                        document.querySelector('.list-answers').innerHTML = response.data.htmlAnswers

                        setTimeout(app.addListenerRadioInputs, 100);
                    }

                    app.btnSubmitHide()
                })
                .catch(function (error) {
                    console.log(error);
                    if (error.message)
                        alert(error.message)
                });
        })
    }
})()