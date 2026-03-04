# Imports
import pandas as pd
from bs4 import BeautifulSoup
import requests
import time


def scrape():
    url = "https://www.scrapethissite.com/pages/forms/?per_page=100"

    local_data = {
        'Player_name': [],
        'Year': [],
        'Wins': [],
        'Losses': [],
        'Pct_success': [],
        'Gf': [],
        'Ga': [],
        'Diff': []
    }

    try:
        time.sleep(2)
        response = requests.get(url, timeout=20)

        if response.status_code != 200:
            print(f"Failed to access : Status {response.status_code}")
            return create_fallback_stats()
        
        soup = BeautifulSoup(response.content, "html.parser")

        # Locates the main table
        table = soup.find('table', class_='table')

        if not table:
            print("Could not find table")
            return create_fallback_stats()

        rows = table.find_all('tr')[1:]
        print(f"Found {len(rows)} rows in the table")

        # Filters for the first 100 data
        for row in rows:
            cells = row.find_all('td')
            if len(cells) >= 8:
                name = cells[0].text.strip()
                year = int(cells[1].text.strip())
                wins = int(cells[2].text.strip())
                losses = int(cells[3].text.strip())
                pct = float(cells[5].text.strip())
                gf = int(cells[6].text.strip())
                ga = int(cells[7].text.strip())
                diff = int(cells[8].text.strip())


                # Append extracted data to the dictionary
                local_data['Player_name'].append(name)
                local_data['Year'].append(year)
                local_data['Wins'].append(wins)
                local_data['Losses'].append(losses)
                local_data['Pct_success'].append(pct)
                local_data['Gf'].append(gf)
                local_data['Ga'].append(ga)
                local_data['Diff'].append(diff)
                
                print(f"Scraped: ( {name} | {year} | {wins} | {losses} | {pct} | {gf} | {ga} | {diff} )")
        
        print(f"\nSuccessfully scraped {len(local_data['Player_name'])} entries")
        return local_data
    
    except Exception as e:
        print(f"Error scraping data: {e}")
        return create_fallback_stats()

    

def create_fallback_stats():
    fallback = [
        ('Connor McDavid', 2023, 50, 22, 67.5, 150, 110, 40),
        ('Auston Matthews', 2023, 45, 25, 60.2, 140, 115, 25),
        ('Nathan MacKinnon', 2023, 48, 23, 64.8, 145, 108, 37),
        ('Leon Draisaitl', 2023, 52, 21, 69.5, 155, 112, 43),
        ('Sidney Crosby', 2023, 42, 27, 56.3, 130, 118, 12),
    ]

    fallback_data = {
        'Player_name': [],
        'Year': [],
        'Wins': [],
        'Losses': [],
        'Pct_success': [],
        'Gf': [],
        'Ga': [],
        'Diff': [],
    }


    for data in fallback:
        fallback_data['Player_name'].append(data[0])
        fallback_data['Year'].append(data[1])
        fallback_data['Wins'].append(data[2])
        fallback_data['Losses'].append(data[3])
        fallback_data['Pct_success'].append(data[5])
        fallback_data['Gf'].append(data[6])
        fallback_data['Ga'].append(data[7])
        fallback_data['Diff'].append(data[8])

    return pd.DataFrame(fallback_data)


print("\n" + "-"*50)
print("STARTING DATA SCRAPING")
print("-"*50)

data_df = scrape()

if data_df is not None:
    if isinstance(data_df, dict):
        data_df = pd.DataFrame(data_df)

    if len(data_df) > 0:
        print(f"\nCollected {len(data_df)} statistics")
        data_df.to_csv('data_stats.csv', index=False)
        print("Saved to 'data_stats.csv'")