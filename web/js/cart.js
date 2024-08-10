let currentIndex = 0;
            const track = document.querySelector('.carousel-track');
            const plans = Array.from(document.querySelectorAll('.plan'));
            const totalPlans = plans.length;

            function moveCarousel(direction) {
                currentIndex = (currentIndex + direction + totalPlans) % totalPlans;
                updateCarousel();
            }

            function updateCarousel() {
                plans.forEach((plan, index) => {
                    const offset = (index - currentIndex + totalPlans) % totalPlans;
                    plan.style.order = offset;
                    if (offset === 0) {
                        plan.classList.add('center');
                        plan.classList.remove('center');
                        plan.classList.remove('hidden');
                    } else if (offset === 1 || offset === totalPlans - 1) {
                        plan.classList.remove('center');
                        plan.classList.remove('hidden');
                    } else {
                        plan.classList.remove('center');
                        plan.classList.add('hidden');
                    }
                });
            }

            document.addEventListener('DOMContentLoaded', updateCarousel);