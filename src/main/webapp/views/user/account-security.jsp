<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h5 class="mb-3 fw-bold">Security</h5>
<hr>
<form>
	<div class="mb-3">
    <label for="currentPassword" class="form-label">Change Password</label>
    <input type="password" class="form-control mb-3" id="currentPassword" placeholder="Enter your old password">
    <input type="password" class="form-control mb-3" id="newPassword" placeholder="New password">
    <input type="password" class="form-control" id="confirmNewPassword" placeholder="Confirm new password">
  </div>
  <button type="submit" class="btn btn-custom">Update Password</button>
</form>
<hr>
<div class="form-group">
   <label class="d-block text-danger">Delete Account</label>
   <p class="text-muted font-size-sm">Once you delete your account, there is no going back. Please be certain.</p>
</div>
<button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteAccountModal" type="button">Delete Account</button>

<!-- Confirm Delete Modal -->
<div class="modal fade" id="deleteAccountModal" tabindex="-1" aria-labelledby="deleteAccountModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteAccountModalLabel">Delete Account</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this account?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-danger">Delete</button>
      </div>
    </div>
  </div>
</div>