<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 1200px;
            width: 100%;
            margin: auto;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .header h2 {
            margin: 0;
            font-size: 24px;
            color: #ffffff;
            background-color: #4caf50;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .header h2:hover {
            background-color: #388e3c;
        }
        .button {
            padding: 10px 20px;
            background-color: #4caf50;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #388e3c;
        }

        .admin-name {
            color: #4caf50;
            margin-left: 10px;
            font-size: 18px;
        }
    </style>
    <body>
        <div class="container">
            <div class="header">
                <a href="adminhomejsp.jsp" class="button">Back to Home</a>
                <h2>Access Logs</h2>
                <span class="admin-name">${acc.username}</span>
            </div>


            <h2>Access Count Per User</h2>
            <canvas id="accessCountPerUserChart"></canvas>
            
            <h2>Access Count Per Day</h2>
            <canvas id="accessCountPerDayChart"></canvas>


            <script>
                // Access Count Per User
                const accessCountPerUserData = ${accessCountPerUserJson};
                const accessCountPerUserLabels = Object.keys(accessCountPerUserData);
                const accessCountPerUserValues = Object.values(accessCountPerUserData);

                const accessCountPerUserCtx = document.getElementById('accessCountPerUserChart').getContext('2d');
                new Chart(accessCountPerUserCtx, {
                    type: 'bar',
                    data: {
                        labels: accessCountPerUserLabels,
                        datasets: [{
                                label: 'Access Count Per User',
                                data: accessCountPerUserValues,
                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });

                // Access Count Per Day
                const accessCountPerDayData = ${accessCountPerDayJson};
                const accessCountPerDayLabels = Object.keys(accessCountPerDayData);
                const accessCountPerDayValues = Object.values(accessCountPerDayData);

                const accessCountPerDayCtx = document.getElementById('accessCountPerDayChart').getContext('2d');
                new Chart(accessCountPerDayCtx, {
                    type: 'line',
                    data: {
                        labels: accessCountPerDayLabels,
                        datasets: [{
                                label: 'Access Count Per Day',
                                data: accessCountPerDayValues,
                                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                                borderColor: 'rgba(153, 102, 255, 1)',
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });


            </script>
    </body>
</html>
