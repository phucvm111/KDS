<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Enter OTP</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: url('assets/image/register.jpg') no-repeat center center fixed;
                background-size: cover;
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .card {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                max-width: 400px;
                width: 100%;
            }
            .otp-input-wrapper {
                display: flex;
                justify-content: center;
                gap: 10px;
                margin-bottom: 20px;
            }
            .otp-box {
                width: 40px;
                height: 50px;
                text-align: center;
                font-size: 20px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .error {
                color: red;
                text-align: center;
                margin-top: 10px;
            }
            .info {
                color: green;
                text-align: center;
                margin-top: 10px;
            }
            #timer {
                text-align: center;
                color: #555;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <form method="post" action="enter_otp_register">
                <h3 class="text-center">Enter OTP</h3>
                <p id="timer">OTP expires in <span id="countdown">03:00</span></p>

                <div class="otp-input-wrapper">
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" required />
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" required />
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" required />
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" required />
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" required />
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" required />
                </div>

                <input type="hidden" name="otp" id="otp" />

                <p class="info">${infoMsg}</p>
                <p class="error">${otpfalse}</p>

                <div class="d-grid">
                    <button type="submit" class="btn btn-danger" id="confirmBtn">Confirm</button>
                    <!-- Resend invokes same servlet with action=resend -->
                    <button type="button" class="btn btn-link mt-2" id="resendBtn">Resend OTP</button>
                </div>
            </form>
        </div>

        <script>
            window.addEventListener('DOMContentLoaded', () => {
                const inputs = document.querySelectorAll('.otp-box');
                const otpHidden = document.getElementById('otp');
                const confirmBtn = document.getElementById('confirmBtn');
                const resendBtn = document.getElementById('resendBtn');
                const timerWrapper = document.getElementById('timer');
                const countdownSpan = document.getElementById('countdown');

                inputs.forEach((input, idx) => {
                    input.addEventListener('input', () => {
                        if (input.value.length === 1 && idx < inputs.length - 1)
                            inputs[idx + 1].focus();
                        otpHidden.value = Array.from(inputs).map(i => i.value).join('');
                    });
                });

                let time = 180;
                const interval = setInterval(() => {
                    if (time >= 0) {
                        const m = Math.floor(time / 60), s = time % 60;
                        countdownSpan.textContent = String(m).padStart(2, '0') + ':' + String(s).padStart(2, '0');

                        time--;
                    } else {
                        clearInterval(interval);
                        inputs.forEach(i => i.disabled = true);
                        confirmBtn.disabled = true;
                        timerWrapper.innerHTML = '<span class="error">OTP expired.</span>';
                    }
                }, 1000);

                resendBtn.addEventListener('click', () => {
                    // gọi cùng servlet với param action=resend
                    window.location.href = 'enter_otp_register?action=resend';
                });
            });
        </script>
    </body>
</html>