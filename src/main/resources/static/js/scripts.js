const API_URL = '/api/reviews';

async function submitReview() {
    const vendorName = document.getElementById('vendorName').value;
    const reviewerName = document.getElementById('reviewerName').value;
    const feedback = document.getElementById('feedback').value;
    const rating = parseInt(document.getElementById('rating').value);
    const isVerified = document.getElementById('isVerified').checked;
const review = { vendorName, reviewerName, feedback, rating, isVerified };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(review)
        });
        if (response.ok) {
            alert('Review submitted successfully!');
            window.location.href = '/view-reviews.html';
        } else {
alert('Error submitting review.');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function loadReviews() {
    try {
        const response = await fetch(API_URL);
        const reviews = await response.json();
        const reviewsList = document.getElementById('reviewsList');
        reviewsList.innerHTML = '';
reviews.forEach(review => {
            const card = document.createElement('div');
            card.className = 'col-md-4 review-card';
            card.innerHTML = `
                <div class="card shadow">
                    <div class="card-body">
                        <h5 class="card-title">${review.vendorName}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">By ${review.reviewerName}</h6>
<p class="card-text">${review.feedback}</p>
                        <p class="card-text"><strong>Rating:</strong> ${review.rating}/5</p>
                        <p class="card-text">${review.verified ? '[Verified]' : ''}</p>
                        <button class="btn btn-warning btn-sm" onclick="editReview('${review.reviewId}')">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteReview('${review.reviewId}')">Delete</button>
                    </div>
</div>
            `;
            reviewsList.appendChild(card);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}


async function loadAdminReviews() {
    try {
        const response = await fetch(API_URL);
        const reviews = await response.json();
        const adminReviewsList = document.getElementById('adminReviewsList');
        adminReviewsList.innerHTML = '';
        reviews.forEach(review => {
            const card = document.createElement('div');
            card.className = 'col-md-4 review-card';
            card.innerHTML = `
<div class="card shadow">
                    <div class="card-body">
                        <h5 class="card-title">${review.vendorName}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">By ${review.reviewerName}</h6>
                        <p class="card-text">${review.feedback}</p>
                        <p class="card-text"><strong>Rating:</strong> ${review.rating}/5</p>
<p class="card-text">${review.verified ? '[Verified]' : ''}</p>
                        <button class="btn btn-danger btn-sm" onclick="deleteReview('${review.reviewId}')">Delete</button>
                    </div>
                </div>
            `;
            adminReviewsList.appendChild(card);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

async function editReview(reviewId) {
    const vendorName = prompt('Enter new vendor name:');
    const feedback = prompt('Enter new feedback:');
    const rating = parseInt(prompt('Enter new rating (1-5):'));

    if (vendorName && feedback && rating >= 1 && rating <= 5) {
        const review = { vendorName, feedback, rating };
        try {
            const response = await fetch(`${API_URL}/${reviewId}`, {
                method: 'PUT',
headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(review)
            });
            if (response.ok) {
                alert('Review updated successfully!');
                loadReviews();
            } else {
                alert('Error updating review.');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}


async function deleteReview(reviewId) {
    if (confirm('Are you sure you want to delete this review?')) {
        try {
            const response = await fetch(`${API_URL}/${reviewId}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Review deleted successfully!');
                loadReviews();
loadAdminReviews();
            } else {
                alert('Error deleting review.');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}
// Load reviews on page load
if (document.getElementById('reviewsList')) {
    loadReviews();
}
if (document.getElementById('adminReviewsList')) {
    loadAdminReviews();
}


