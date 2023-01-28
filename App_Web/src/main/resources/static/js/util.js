export const isClickOutside = (elm, target) => {
  while (target) {
    if (target === elm) {
      return false;
    }
    target = target.parentElement;
  }
  return true;
};
