$(() => {
  const page = getPage();
  renderPaginationButtons(page);

  function getPage() {
    const $pageMeta = $('.pagination .meta');
    return {
      'url': $pageMeta.attr('url'),
      'first': $pageMeta.attr('first'),
      'last': $pageMeta.attr('last'),
      'total': parseInt($pageMeta.attr('total')),
      'number': parseInt($pageMeta.attr('number'))
    };
  }

  function renderPaginationButtons(page) {
    $(".current").text(page.number + 1);
    $(".pagination button").off();
    const prevPage = Math.max(0, page.number - 1);
    const lastPage = page.total - 1;
    const nextPage = Math.min(lastPage, page.number + 1);
    $(".first").on("click", () => window.location = `${page.url}?page=0`);
    $(".prev").on("click", () => window.location = `${page.url}?page=${prevPage}`);
    $(".next").on("click", () => window.location = `${page.url}?page=${nextPage}`);
    $(".last").on("click", () => window.location = `${page.url}?page=${lastPage}`);
  }
});
