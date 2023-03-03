<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- Modal -->
<div class="modal fade" id="write_review_modal" tabindex="-1" aria-labelledby="write_review_modal_label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="write_review_modal_label">Write a Review</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <div class="modal-body">
              
        <div id="submit-error" class="alert alert-danger alert-dismissible fade hide" role="alert">
		  <strong>Something went wrong!</strong> Please try again later.
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		
        <div class="mb-3">
		  <label for="write-review-title" class="form-label fw-bold">Title</label>
		  <input type="text" class="form-control" id="write-review-title" placeholder="Summarize your rating">
		</div>
		<div class="my-3">
		  <label for="write-review-description" class="form-label fw-bold">Description</label>
		  <textarea class="form-control" id="write-review-description" rows="3"></textarea>
		</div>
		<div class="mt-3">
		  <label for="write-review-rating" class="form-label fw-bold">Rating</label>
		  <div id="write-review-rating">
			<jsp:include page="star-rating.jsp">
				<jsp:param name="rating" value="0" />
			</jsp:include>
		  </div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-custom" onclick="submitReview()">Submit</button>
      </div>
    </div>
  </div>
</div>