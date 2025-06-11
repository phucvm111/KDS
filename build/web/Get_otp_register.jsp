<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Member Register</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    * {
      box-sizing: border-box;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    body, html {
      margin: 0;
      padding: 0;
      height: 100%;
    }

    body {
      background: url('assets/image/register.jpg') no-repeat center center fixed;
      background-size: cover;
      position: relative;
    }

    .overlay {
      position: absolute;
      top: 0; left: 0;
      width: 100%; height: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      z-index: 1;
    }

    .form-wrapper {
      position: relative;
      z-index: 2;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100%;
      padding: 20px;
    }

    .card {
      background: #fff;
      border-radius: 16px;
      padding: 40px 30px;
      max-width: 400px;
      width: 100%;
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.25);
    }

    .card-title {
      font-size: 28px;
      text-align: center;
      margin-bottom: 10px;
      font-weight: bold;
      color: #333;
    }

    .card-subtitle {
      text-align: center;
      font-size: 14px;
      color: #666;
      margin-bottom: 20px;
    }

    .form-control {
      border-radius: 30px;
      padding: 12px 20px;
      font-size: 15px;
    }

    .btn-primary {
      background-color: #e63946;
      border: none;
      padding: 12px;
      font-weight: bold;
      width: 100%;
      border-radius: 30px;
    }

    .btn-primary:hover {
      background-color: #c5303e;
    }

    .error-message {
      color: red;
      font-size: 14px;
      text-align: center;
      margin-top: 10px;
    }

    @media (max-width: 576px) {
      .card {
        padding: 30px 20px;
      }
    }
  </style>
</head>
<body>

  <div class="overlay"></div>

  <div class="form-wrapper">
    <div class="card">
      <form method="post" action="get_otp_register">
        <h2 class="card-title">Member Register</h2>
        <p class="card-subtitle">Please enter your email address to receive an OTP code.</p>

        <div class="mb-3">
          <input type="text" class="form-control" name="email" id="email" placeholder="Email" title="Must contain @" required />
        </div>

        <p class="error-message">${emailexisted}</p>

        <button type="submit" class="btn btn-primary">GET OTP</button>
      </form>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
