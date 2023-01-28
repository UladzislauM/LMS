import {isClickOutside} from "./util";

export const getNotification = (message, title='Information') => {
  const $notification = $(`
    <div class="notification">
        <div class="notification_title">${title}</div>
      <div class="notification_content">${message}</div>
      <button class="notification_ok">OK</button>
    </div>
  `);

  const clickOutsideCheck = (e) => {
    if (isClickOutside($notification.get(0), e.target)) {
      e.stopImmediatePropagation();
      e.preventDefault();
    }
  };

  const $button = $notification.find('.notification_ok');
  $button.on('click', () => {
    $notification.remove();
    $('.main').css('opacity', '');
    $('body').get(0).removeEventListener('click', clickOutsideCheck, true);
  });


  $('body').get(0).addEventListener('click', clickOutsideCheck, true);

  const notification = {};
  notification.element = $notification;
  notification.display = () => {
    $('.main').css('opacity', '0.5');
    $('body').prepend($notification);
  }
  return notification;
};
