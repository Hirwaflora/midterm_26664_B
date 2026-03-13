document.addEventListener('DOMContentLoaded', () => {

    // --- FORM REGISTRATION LOGIC (index.html) ---
    const supplierForm = document.getElementById('supplierForm');
    if (supplierForm) {
        supplierForm.addEventListener('submit', (e) => {
            e.preventDefault();

            // Gather form data
            const newSupplier = {
                id: Date.now().toString(),
                supplierName: document.getElementById('supplierName').value,
                tinNo: document.getElementById('tinNo').value,
                rssbNo: document.getElementById('rssbNo').value,
                country: document.getElementById('country').value,
                regDate: document.getElementById('regDate').value,
                bizType: document.getElementById('bizType').value,
                currency: document.getElementById('currency').value,
                capital: document.getElementById('capital').value,
                employees: document.getElementById('employees').value,
                poBox: document.getElementById('poBox').value,
                address: document.getElementById('address').value,
                tel1: document.getElementById('tel1').value,
                tel2: document.getElementById('tel2').value,
                fax: document.getElementById('fax').value,
                email: document.getElementById('email').value,
                website: document.getElementById('website').value,

                // Bank Details
                bankName: document.getElementById('bankName').value,
                accountNo: document.getElementById('accountNo').value,
                accountHolder: document.getElementById('accountHolder').value,

                // Rep Details
                repName: document.getElementById('repName').value,
                repId: document.getElementById('repId').value,
                repPhone: document.getElementById('repPhone').value,
                repEmail: document.getElementById('repEmail').value,
                repPoBox: document.getElementById('repPoBox').value,

                status: 'Pending Verification',
                registeredAt: new Date().toLocaleString()
            };

            // Post to Spring Boot Backend
            fetch('/api/suppliers/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newSupplier)
            })
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(err => { throw new Error(err); });
                    }
                    return response.json();
                })
                .then(data => {
                    // Show success message
                    const msgDiv = document.getElementById('formMessage');
                    msgDiv.className = 'message success-text mt-4';
                    msgDiv.innerHTML = 'Registration successful! Your application is pending review.';
                    msgDiv.classList.remove('hidden');

                    // Reset form
                    supplierForm.reset();

                    setTimeout(() => {
                        msgDiv.classList.add('hidden');
                    }, 5000);
                })
                .catch(error => {
                    const msgDiv = document.getElementById('formMessage');
                    msgDiv.className = 'message error-text mt-4';
                    msgDiv.innerHTML = 'Error: ' + error.message;
                    msgDiv.classList.remove('hidden');
                });
        });
    }

    // --- ADMIN LOGIN LOGIC (login.html) ---
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const pwd = document.getElementById('adminPassword').value;
            const errDiv = document.getElementById('loginError');

            if (pwd === 'admin123') {
                sessionStorage.setItem('adminLoggedIn', 'true');
                window.location.href = 'admin.html';
            } else {
                errDiv.classList.remove('hidden');
            }
        });
    }

    // --- ADMIN DASHBOARD LOGIC (admin.html) ---
    const supplierTableBody = document.getElementById('supplierTableBody');
    if (supplierTableBody) {

        // Fetch from Backend
        fetch('/api/suppliers')
            .then(res => res.json())
            .then(suppliers => {
                window.loadedSuppliers = suppliers; // Cache for modal
                document.getElementById('totalSuppliers').textContent = suppliers.length;

                if (suppliers.length === 0) {
                    document.getElementById('noDataMessage').classList.remove('hidden');
                    document.getElementById('suppliersTable').classList.add('hidden');
                } else {
                    suppliers.forEach(supplier => {
                        const tr = document.createElement('tr');
                        tr.innerHTML = `
                            <td>${supplier.tinNo}</td>
                            <td><strong>${supplier.supplierName}</strong></td>
                            <td>${supplier.country}</td>
                            <td>${supplier.bizType}</td>
                            <td>${supplier.email}</td>
                            <td><span class="status-badge">${supplier.status}</span></td>
                            <td>
                                <button class="btn-outline btn-sm" onclick="viewDetails(${supplier.id})">View Details</button>
                            </td>
                        `;
                        supplierTableBody.appendChild(tr);
                    });
                }
            })
            .catch(err => console.error('Failed to load suppliers:', err));

        // Logout
        document.getElementById('logoutBtn').addEventListener('click', () => {
            sessionStorage.removeItem('adminLoggedIn');
            window.location.href = 'login.html';
        });

        // Modal Logic
        const modal = document.getElementById('supplierModal');
        const closeModal = document.querySelector('.close-modal');

        closeModal.addEventListener('click', () => {
            modal.classList.add('hidden');
        });

        window.onclick = function (event) {
            if (event.target == modal) {
                modal.classList.add('hidden');
            }
        }
    }
});

// Global function to view details from table
window.viewDetails = function (id) {
    const suppliers = window.loadedSuppliers || [];
    const supplier = suppliers.find(s => s.id === id);
    if (!supplier) return;

    document.getElementById('modalSupplierName').textContent = supplier.supplierName + ' (' + supplier.tinNo + ')';

    let detailsHtml = `
        <div class="form-section" style="grid-column: 1 / -1; margin-bottom: 1rem;">
            <h2>General Info</h2>
            <div class="modal-grid mt-2">
                <div class="detail-item"><strong>Date Applied</strong><span>${supplier.registeredAt}</span></div>
                <div class="detail-item"><strong>Country</strong><span>${supplier.country}</span></div>
                <div class="detail-item"><strong>TIN No</strong><span>${supplier.tinNo}</span></div>
                <div class="detail-item"><strong>RSSB No</strong><span>${supplier.rssbNo}</span></div>
                <div class="detail-item"><strong>Business Type</strong><span>${supplier.bizType}</span></div>
                <div class="detail-item"><strong>Capital</strong><span>${supplier.capital} ${supplier.currency}</span></div>
                <div class="detail-item"><strong>Address</strong><span>${supplier.address}</span></div>
                <div class="detail-item"><strong>Email</strong><span>${supplier.email}</span></div>
                <div class="detail-item"><strong>Telephone</strong><span>${supplier.tel1} ${supplier.tel2 ? '/ ' + supplier.tel2 : ''}</span></div>
            </div>
        </div>
        <div class="form-section">
            <h2>Bank Details</h2>
            <div class="detail-item"><strong>Bank Name</strong><span>${supplier.bankName}</span></div>
            <div class="detail-item"><strong>Account No</strong><span>${supplier.accountNo}</span></div>
            <div class="detail-item"><strong>Account Holder</strong><span>${supplier.accountHolder}</span></div>
        </div>
        <div class="form-section">
            <h2>Representative Info</h2>
            <div class="detail-item"><strong>Name</strong><span>${supplier.repName}</span></div>
            <div class="detail-item"><strong>National ID</strong><span>${supplier.repId}</span></div>
            <div class="detail-item"><strong>Phone</strong><span>${supplier.repPhone}</span></div>
            <div class="detail-item"><strong>Email</strong><span>${supplier.repEmail}</span></div>
        </div>
    `;

    document.getElementById('modalContent').innerHTML = detailsHtml;
    document.getElementById('supplierModal').classList.remove('hidden');
};
