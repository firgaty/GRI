import wget
import pathlib
import os
import subprocess
import urllib


base_url = "https://snap.stanford.edu/data/"
base_url2 = "https://snap.stanford.edu/data/bigdata/communities/"
base_url3 = "https://who.rocq.inria.fr/Laurent.Viennot/t/"

dest_folder = "resources/graph/"

file_urls = [
    ("g.txt", 6),
    ("as20000102.txt.gz", 13895),
    ("as-caida20071105.txt.gz", 106762),
    ("as-skitter.txt.gz", 11095298),
    ("roadNet-TX.txt.gz", 1921660),
    ("roadNet-CA.txt.gz", 2766607),
    ("ca-AstroPh.txt.gz", 198110),
    ("com-dblp.ungraph.txt.gz", 1049866),
    ("email-Enron.txt.gz", 183831),
    ("soc-pokec-relationships.txt.gz", 30622564),
    ("web-BerkStan.txt.gz", 7600595),
    # ("bigdeg.txt.gz", 0), # Can't find it
]

final_paths = []
results = []

# Download
for file, _ in file_urls:
    final_name = file

    if final_name[-3:] == ".gz":
        final_name = final_name[:-3]

    path = pathlib.Path(dest_folder).joinpath(file)
    final_path = pathlib.Path(dest_folder).joinpath(final_name)
    final_paths.append(final_path)

    if not final_path.exists() and not path.exists():
        try:
            wget.download(base_url + file, dest_folder)
        except urllib.error.HTTPError:
            try:
                wget.download(base_url2 + file, dest_folder)
            except urllib.error.HTTPError:
                try:
                    wget.download(base_url3 + file, dest_folder)
                except urllib.error.HTTPError:
                    print(f"File not found: {file}")

    if not final_path.exists():
        subprocess.run(
            ["gzip", "-dv", path],
        )

# Make
subprocess.run(["make"])
total = len(final_paths)

# Run
for i, p in enumerate(final_paths):
    args = [
        "java",
        "-Xms700M",
        "-Xmx700M",
        "TP2",
        "all",
        str(p),
        str(file_urls[i][1]),
        "-1",
    ]

    print(f"( {i + 1:>2}/ {total:>2}) {p}: {' '.join(args)}")
    result = subprocess.run(
        args,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
    )

    print(result.stdout.decode("utf-8"))
    results.append([file_urls[i][0]] + result.stdout.decode("utf-8").split("\n")[:4])

# Write results
with open("resultats.txt", "w") as file:
    file.write("#graph\t2-sweep\t4-sweep\tsum-sweep\texact\n")

    for r in results:
        file.write("\t".join(r) + "\n")