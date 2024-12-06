document.addEventListener("DOMContentLoaded", () => {
  const headerEl = document.querySelector("header");
  if (!headerEl) {
    console.error("Header element not found.");
    return;
  }

  const headerMenuEls = [...headerEl.querySelectorAll("ul.menu > li")];
  const searchWrapEl = headerEl.querySelector(".search-wrap");
  const searchStarterEl = headerEl.querySelector(".search-starter a");
  const searchCloserEl = searchWrapEl.querySelector(".search-closer");
  const shadowEl = searchWrapEl.querySelector(".shadow");
  const searchInputEl = searchWrapEl.querySelector("input");
  const searchDelayEls = [...searchWrapEl.querySelectorAll("li")];

  // Check if critical elements exist
  if (!searchStarterEl || !searchCloserEl || !shadowEl) {
    console.error("Some critical elements are missing.");
    return;
  }

  // Add event listeners
  searchStarterEl.addEventListener("click", (event) => {
    event.preventDefault();
    showSearch();
  });

  searchCloserEl.addEventListener("click", (event) => {
    event.preventDefault();
    event.stopPropagation();
    hideSearch();
  });

  shadowEl.addEventListener("click", hideSearch);

  function showSearch() {
    headerEl.classList.add("searching");
    stopScroll();

    headerMenuEls.reverse().forEach((el, index) => {
      el.style.transitionDelay = (index * 0.4) / headerMenuEls.length + "s";
    });

    searchDelayEls.forEach((el, index) => {
      el.style.transitionDelay = (index * 0.4) / searchDelayEls.length + "s";
    });

    setTimeout(() => {
      searchInputEl.focus();
    }, 700);
  }

  function hideSearch() {
    headerEl.classList.remove("searching");
    playScroll();

    headerMenuEls.reverse().forEach((el, index) => {
      el.style.transitionDelay = (index * 0.4) / headerMenuEls.length + "s";
    });

    searchDelayEls.reverse().forEach((el, index) => {
      el.style.transitionDelay = (index * 0.4) / searchDelayEls.length + "s";
    });

    searchDelayEls.reverse();
    searchInputEl.value = "";
  }

  function stopScroll() {
    document.documentElement.classList.add("fixed");
  }

  function playScroll() {
    document.documentElement.classList.remove("fixed");
  }
});