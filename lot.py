import os

def count_lines_of_test(base_dir, lines=0, header=True, begin_base_dir=None):
    if header:
        print('{:>10} | {:<20}'.format('LOT', 'FILE'))
        print('{:->11}|{:->20}'.format('', ''))

    for thing in os.listdir(base_dir):
        thing = os.path.join(base_dir, thing)
        if os.path.isfile(thing):
            if thing.endswith('.java'):
                with open(thing, 'r') as f:
                    newlines = f.readlines()
                    newlines = [line for line in newlines if line.strip() != '']
                    newlines = len(newlines)
                    lines += newlines

                    if begin_base_dir is not None:
                        reldir_of_thing = '.' + thing.replace(begin_base_dir, '')
                    else:
                        reldir_of_thing = '.' + thing.replace(base_dir, '')

                    print('{:>10} | {:<20}'.format(
                            newlines, reldir_of_thing))


    for thing in os.listdir(base_dir):
        thing = os.path.join(base_dir, thing)
        if os.path.isdir(thing):
            lines = count_lines_of_test(thing, lines, header=False, begin_base_dir=base_dir)

    return lines

if __name__ == '__main__':
    print(count_lines_of_test('./src/test'))