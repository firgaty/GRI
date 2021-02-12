import wget
import pathlib
import os
import subprocess
import urllib
from typing import Iterator, Tuple


base_url = [
    "https://files.inria.fr/gang/snap/",
    "https://who.rocq.inria.fr/Laurent.Viennot/t/",
]

dest_folder = "resources/graph/"

files = {
    "test.txt": {
        "nodes": 7,
        "input_node": 1,
        "expected_output": {
            "triangles": [2],
            "clust": [0.38889, 0.23077],
            "k-coeur": [2, 4],
        },
    },
    "as20000102-simple.txt": {
        "nodes": 13895,
        "input_node": 123,
        "expected_output": {
            "triangles": [7],
            "clust": [0.25222, 0.00480],
            "k-coeur": [12, 21],
        },
    },
    "as-caida20071105-simple.txt": {
        "nodes": 106762,
        "input_node": 123,
        "expected_output": {
            "triangles": [0],
            "clust": [0.08412, 0.00366],
        },
    },
    "as-skitter-simple.txt": {
        "nodes": 11095298,
        "input_node": 123,
        "expected_output": {
            "triangles": [291],
            "clust": [0.25815, 0.00269],
            "k-coeur": [111, 222],
        },
    },
    "roadNet-TX-simple.txt": {
        "nodes": 1921660,
        "input_node": 123,
        "expected_output": {
            "triangles": [0],
            "clust": [0.04655, 0.03011],
            "k-coeur": [3, 1579],
        },
    },
    "roadNet-CA-simple.txt": {
        "nodes": 2766607,
        "input_node": 123,
        "expected_output": {
            "triangles": [0],
            "clust": [0.04623, 0.03019],
            "k-coeur": [3, 4568],
        },
    },
    "ca-AstroPh-simple.txt": {
        "nodes": 198110,
        "input_node": 123,
        "expected_output": {
            "triangles": [0],
            "clust": [0.08882, 0.15900],
            "k-coeur": [56, 57],
        },
    },
    "email-Enron-simple.txt": {
        "nodes": 183831,
        "input_node": 123,
        "expected_output": {
            "triangles": [33],
            "clust": [0.49698, 0.04266],
            "k-coeur": [43, 275],
        },
    },
    "bigdeg.txt": {
        "nodes": 2010000,
        "input_node": 200531,
        "expected_output": {
            "triangles": [1000000],
        },
    },
    "soc-pokec-relationships-simple.txt": {
        "nodes": 30622564,
        "input_node": 123,
        "expected_output": {
            "triangles": [197],
            "clust": [0.08052, 0.02043],
            "k-coeur": [46, 221],
        },
    },
    "web-BerkStan-simple.txt": {
        "nodes": 7600595,
        "input_node": 123,
        "expected_output": {
            "triangles": [2],
            "clust": [0.59670, 0.00450],
            "k-coeur": [201, 392],
        },
    },
}


def walk_path(
    path: str, file_extentions: list[str] = [".txt"]
) -> Iterator[Tuple[str, str]]:
    for (dirpath, _, filenames) in os.walk(path):
        for filename in filenames:
            for ext in file_extentions:
                if filename.endswith(ext):
                    yield (os.path.join(dirpath, filename), filename)
                    break


final_paths = []
results = []

# Download
for file in files:
    gzip_name = file + ".gz"

    path = pathlib.Path(dest_folder).joinpath(gzip_name)
    final_path = pathlib.Path(dest_folder).joinpath(file)
    final_paths.append(final_path)

    if not final_path.exists() and not path.exists():

        for i in range(len(base_url)):
            try:
                wget.download(base_url[i] + gzip_name, dest_folder)
                break
            except urllib.error.HTTPError:
                continue

    if not final_path.exists():
        subprocess.run(
            ["gzip", "-dv", path],
        )

# Make
subprocess.run(["make"])
total = len(final_paths)

# Run

path_and_names = sorted(walk_path(dest_folder), key=lambda x: x[0])
total = len(path_and_names)
functions = [("triangles", ["input_node"]), ("clust", []), ("k-coeur", [])]

for i, path_name in enumerate(path_and_names):
    base_args = ["java", "-Xms700M", "-Xmx700M", "TP3"]

    file_dict = files[path_name[1]]

    print(f"( {i + 1:>2}/ {total:>2}) {path_name[1]}:")

    for func, args in functions:

        added_args = [func, path_name[0], str(file_dict["nodes"])]

        if func not in file_dict["expected_output"]:
            continue
        
        for arg in args:
            added_args.append(str(file_dict[arg]))

        print(f" |- {func}: {' '.join(base_args + added_args)}")
        result = subprocess.run(
            base_args + added_args,
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,
        )

        # print(result.stdout.decode("utf-8"))
        result = result.stdout.decode("utf-8").strip().split("\n")

        for i, output in enumerate(file_dict["expected_output"][func]):
            if str(output) != result[i]:
                output_str = map(str, file_dict['expected_output'][func])
                print(
                    f" |- ! NOT OK: given ({', '.join(result)}) - expected ({', '.join(output_str)})"
                )
                break
        else:
            print(" |- ! OK")
