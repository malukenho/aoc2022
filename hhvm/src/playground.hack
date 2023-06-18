<?hh // strict

function binary_search($needle, $haystack, $left = null, $right = null) {
    if ($left === null) {
        $left = 0;
    }
    if ($right === null) {
        $right = \count($haystack) - 1;
    }

    echo "Searching: [" . \implode(", ", \array_slice($haystack, $left, $right - 1)) . "]\n";

    if ($left > $right) {
        return null;
    }

    $mid = floor(($left+$right) / 2);

    if ($needle === $haystack[$mid]) {
        return $mid;
    } elseif ($needle < $haystack[$mid]) {
        return binary_search($needle, $haystack, $left, $mid - 1);
    } elseif ($needle > $haystack[$mid]) {
        return binary_search($needle, $haystack, $mid + 1, $right);
    }
}

\var_dump(binary_search(13, [1,4,8,11,13,16,19,19]));

