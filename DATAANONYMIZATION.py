import pandas as pd

# Original dataset
data = pd.DataFrame({
    'Name': ['John Doe', 'Jane Smith', 'Michael Johnson'],
    'Email': ['johndoe@example.com', 'janesmith@example.com',
              'michaeljohnson@example.com'],
    'Age': [25, 30, 35]
})

# Masking sensitive attributes
data['Name'] = 'XXXXXXXXXX'
data['Email'] = 'xxxxxxxxxxxxxx'

# Output anonymized dataset
print(data)
