import {getNotification} from './notification.js';

$(() => {
  const password = $('#input-password').get(0);
  const passwordConfirmation = $('#input-confirm-password').get(0);
  const $submit = $('#input-submit');

  $submit.on('click', (e) => {
    if (password.value !== passwordConfirmation.value) {
      e.preventDefault();
      const $notification = getNotification('Passwords do not match!');
      $notification.display();
    }
  });
});
